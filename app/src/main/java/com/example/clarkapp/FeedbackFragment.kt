package com.example.clarkapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_feedback.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FeedbackFragment : Fragment() {
    lateinit var viewModel: FeedbackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(FeedbackViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(FeedbackViewModel::class.java)

        submit_button.setOnClickListener {
            var name = name_editText.text.toString()
            var id = name.hashCode().toString()
            var date = Calendar.getInstance().time.toString()
            var email = email_editText.text.toString()
            var feedback = feedback_editText.text.toString()

            viewModel.addFeedback(
                FeedbackObject(
                    id,
                    date,
                    name,
                    email,
                    feedback
                )
            )
        }


    }


}
