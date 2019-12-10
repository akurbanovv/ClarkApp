package com.example.clarkapp


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.fragment_safety.*



/**
 * A simple [Fragment] subclass.
 */
class SafetyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_safety, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ems_text.setText("(508) 793-7575")
        up_text.setText("(508) 793-7575")
        hs_text.setText("(508) 793-7467")
        cw_text.setText("(508) 793-7678")


        ems_text.setOnClickListener{
            makeCall("5087937575")
        }

        up_text.setOnClickListener {
            makeCall("5087937575")
        }

        hs_text.setOnClickListener {
            makeCall("5087937467")
        }

        cw_text.setOnClickListener {
            makeCall("5087937678")
        }

        emergency_text.setOnClickListener {
            var url = "http://www2.clarku.edu/offices/campussafety/index.cfm"
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(Color.parseColor("#BC0005"))
            builder.enableUrlBarHiding()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }

    fun makeCall(tel: String){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.setData(Uri.parse("tel:$tel"))
        startActivity(callIntent)
    }

}
