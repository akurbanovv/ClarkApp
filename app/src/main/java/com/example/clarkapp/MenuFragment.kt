package com.example.clarkapp


import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = activity?.run{
            ViewModelProviders.of(this).get(ViewModel::class.java)
        }?: throw Exception("activity invalid")
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_mapFragment)
        }
        events_button.setOnClickListener {
            loadWeb("https://clarku.campuslabs.com/engage/events")
        }
        news_button.setOnClickListener {
            loadWeb("https://clarknow.clarku.edu/stories/")
        }
        feedback_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_feedbackFragment)
        }
        help_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_safetyFragment)
        }
        athletics_button.setOnClickListener {
            loadWeb("https://www.clarkathletics.com/landing/index")
            // findNavController().navigate(R.id.action_menuFragment_to_hoursFragment)
        }
        library_button.setOnClickListener {
            viewModel.currentFragment.value = "library"
            findNavController().navigate(R.id.action_menuFragment_to_recyclerFragment)
        }
        map_button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_mapFragment)
        }
        res_button.setOnClickListener {
            viewModel.currentFragment.value = "resources"
            findNavController().navigate(R.id.action_menuFragment_to_recyclerFragment)
        }
        courses_button.setOnClickListener {
            viewModel.currentFragment.value = "courses"
            findNavController().navigate(R.id.action_menuFragment_to_recyclerFragment)
        }
    }

    private fun loadWeb(url: String){
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(Color.parseColor("#BC0005"))
        builder.enableUrlBarHiding()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

}
