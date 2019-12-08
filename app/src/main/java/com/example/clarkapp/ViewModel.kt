package com.example.clarkapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewModel (application: Application) : AndroidViewModel(application) {

    var database = MutableLiveData<DatabaseReference>()
    var resourcesList = MutableLiveData<ArrayList<ResourceObject>>()
    var currentWebPage = MutableLiveData<String>()

    init {
        database.value = FirebaseDatabase.getInstance().reference
        resourcesList.value = ArrayList()
        currentWebPage.value = ""

        database.value?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                resourcesList.value!!.clear()
                p0.child("resources").child("resources").children.forEach { resource ->
                    resource.getValue(ResourceObject::class.java)?.let {
                        resourcesList.value?.add(it)
                    }
                }
            }
        })
    }

    fun addFeedback(feedback: FeedbackObject) {
        viewModelScope.launch {
            var finish = async { upload(feedback) }
            finish.await()
        }
    }

    suspend fun upload(feedback: FeedbackObject) = withContext(Dispatchers.IO) {
        database.value
            ?.child("feedbacks")
            ?.child(feedback.name)
            ?.child(feedback.date)
            ?.setValue(feedback)

        println("uploaded")
    }
}