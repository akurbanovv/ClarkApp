package com.example.clarkapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_feedback.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FeedbackFragment : Fragment() {
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        submit_button.setOnClickListener {
            val name = name_editText.text.toString()
            val id = name.hashCode().toString()
            val date = Calendar.getInstance().time.toString()
            val email = email_editText.text.toString()
            val feedback = feedback_editText.text.toString()

            if (name_editText.text.isEmpty() || email_editText.text.isEmpty() || feedback_editText.text.isEmpty()) {
                Toast.makeText(getActivity(), "Please don't leave empty fields. ", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addFeedback(FeedbackObject(id, date, name, email, feedback))
                findNavController().navigate(R.id.action_feedbackFragment_to_menuFragment)
            }
        }

        cancel_button.setOnClickListener {
            findNavController().navigate(R.id.action_feedbackFragment_to_menuFragment)
        }
    }
}
