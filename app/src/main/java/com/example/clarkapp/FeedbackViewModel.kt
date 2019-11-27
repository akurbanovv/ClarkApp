package com.example.clarkapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FeedbackViewModel (application: Application) : AndroidViewModel(application) {

    var database = MutableLiveData<DatabaseReference>()

    init {
        database.value = FirebaseDatabase.getInstance().reference
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