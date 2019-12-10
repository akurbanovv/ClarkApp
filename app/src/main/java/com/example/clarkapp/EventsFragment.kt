package com.example.clarkapp


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_events.*
import android.widget.ProgressBar
import android.graphics.Bitmap
import android.view.KeyEvent
import android.widget.TextView
import androidx.core.view.isInvisible
import android.view.KeyEvent.KEYCODE_BACK
import androidx.activity.OnBackPressedCallback





/**
 * A simple [Fragment] subclass.
 */
class EventsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

//    private lateinit var webView: WebView
//    private var loadingText: TextView? = null
//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        loadingText = view.findViewById(R.id.loading_textView)
//        webView = view.findViewById(R.id.webView)
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                view?.visibility = View.INVISIBLE
//            }
//
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                webView.loadUrl("javascript:(function() { " +
//                        "var head = document.getElementById(‘discovery-bar’).style.display='none'; " +
//                        "})()")
//                return true
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                loadingText?.visibility = View.GONE
//                view?.visibility = View.VISIBLE
//
//                webView.loadUrl("javascript:(function() { " +
//                        "var head = document.getElementById(‘discovery-bar’).style.display='none'; " +
//                        "})()")
//
//                super.onPageFinished(view, url)
//            }
//
//
//        }
//
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl("https://clarku.campuslabs.com/engage/events")
//    }
}


