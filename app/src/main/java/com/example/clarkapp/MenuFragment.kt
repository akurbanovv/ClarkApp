package com.example.clarkapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_mapFragment)
        }
        events_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_eventsFragment)
        }
        news_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_newsFragment)
        }
        feedback_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_feedbackFragment)
        }
        help_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_helpFragment)
        }
        hours_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_hoursFragment)
        }
        library_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_libraryFragment)
        }
        map_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_mapFragment)
        }
        res_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_resourcesFragment)
        }
        courses_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_coursesFragment)
        }



    }


}
