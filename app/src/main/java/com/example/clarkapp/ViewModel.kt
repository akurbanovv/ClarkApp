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
    var resourcesList = MutableLiveData<ArrayList<RecyclerListObject>>()
    var libraryList = MutableLiveData<ArrayList<RecyclerListObject>>()
    var currentWebPage = MutableLiveData<String>()
    var currentFragment = MutableLiveData<String>()
    var coursesList = MutableLiveData<ArrayList<RecyclerListObject>>()

    init {
        database.value = FirebaseDatabase.getInstance().reference
        resourcesList.value = ArrayList()
        libraryList.value = ArrayList()
        coursesList.value = ArrayList()
        currentWebPage.value = ""
        currentFragment.value = ""

        database.value?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                resourcesList.value!!.clear()
                p0.child("resources").child("resources").children.forEach { resource ->
                    resource.getValue(RecyclerListObject::class.java)?.let {
                        resourcesList.value?.add(it)
                    }
                }

                libraryList.value!!.clear()
                p0.child("library").child("library").children.forEach { library ->
                    library.getValue(RecyclerListObject::class.java)?.let {
                        libraryList.value?.add(it)
                    }
                }

                coursesList.value!!.clear()
                p0.child("courses").child("courses").children.forEach { library ->
                    library.getValue(RecyclerListObject::class.java)?.let {
                        coursesList.value?.add(it)
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