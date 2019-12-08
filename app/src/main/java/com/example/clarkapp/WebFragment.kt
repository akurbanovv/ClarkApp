package com.example.clarkapp


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

/**
 * A simple [Fragment] subclass.
 */
class WebFragment : Fragment() {

    lateinit var viewModel: ViewModel
    private lateinit var webView: WebView
    private var loadingText: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ViewModel::class.java)
        } ?: throw Exception("activity invalid")
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingText = view.findViewById(R.id.loading_textView)
        webView = view.findViewById(R.id.webView)
        webView.webViewClient = object : WebViewClient() {


            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                view?.visibility = View.INVISIBLE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementById(‘discovery-bar’).style.display='none'; " +
                        "})()")
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                loadingText?.visibility = View.GONE
                view?.visibility = View.VISIBLE

                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementById(‘discovery-bar’).style.display='none'; " +
                        "})()")

                super.onPageFinished(view, url)
            }


        }

        webView.settings.javaScriptEnabled = true
        print(viewModel.currentWebPage.value.toString())
        webView.loadUrl(viewModel.currentWebPage.value.toString())
    }

}
