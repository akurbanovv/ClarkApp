package com.example.clarkapp


import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recycler.*

class RecyclerViewFragment : Fragment() {
    lateinit var viewModel: ViewModel
    lateinit var viewAdapter: RecyclerViewAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ViewModel::class.java)
        } ?: throw Exception("activity invalid")

        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = RecyclerViewAdapter(ArrayList()) {
            openUrl(it)
        }

        resources_RecyclerView.apply {
            this.layoutManager = viewManager
            this.adapter = viewAdapter
        }

        when (viewModel.currentFragment.value) {
            "resources" -> {
                view.findViewById<TextView>(R.id.recycler_text).setText("On-Campus Resources")
                viewModel.resourcesList.observe(this, Observer {
                    viewAdapter.recyclerObjList = it
                    viewAdapter.notifyDataSetChanged()
                })
            }

            "library" -> {
                view.findViewById<TextView>(R.id.recycler_text).setText("Goddard Library")
                viewModel.libraryList.observe(this, Observer {
                    viewAdapter.recyclerObjList = it
                    viewAdapter.notifyDataSetChanged()
                })
            }

            "courses" -> {
                view.findViewById<TextView>(R.id.recycler_text).setText("Courses Information")
                viewModel.coursesList.observe(this, Observer {
                    viewAdapter.recyclerObjList = it
                    viewAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    private fun openUrl(recyclerListObj: RecyclerListObject) {
        viewModel.currentWebPage.value = recyclerListObj.url

        val builder = CustomTabsIntent.Builder()
        builder.enableUrlBarHiding()
        builder.setToolbarColor(Color.parseColor("#BC0005"))

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(recyclerListObj.url))
    }

    class RecyclerViewAdapter(
        var recyclerObjList: ArrayList<RecyclerListObject>,
        private val clickListener: (RecyclerListObject) -> Unit
    ) :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_reclist, parent, false)
            return RecyclerViewHolder(view)
        }

        override fun getItemCount(): Int {
            return recyclerObjList.size
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.bind(recyclerObjList[position], clickListener)
        }

        class RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(recListObj: RecyclerListObject, clickListener: (RecyclerListObject) -> Unit) {
                view.run {
                    view.findViewById<TextView>(R.id.name_text).text = recListObj.name
                    view.findViewById<LinearLayout>(R.id.lin_layout)
                        .setOnClickListener { clickListener(recListObj) }
                }
            }
        }
    }

}
