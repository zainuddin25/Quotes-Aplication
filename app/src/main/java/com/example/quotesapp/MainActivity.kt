package com.example.quotesapp

import android.app.ProgressDialog
import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RequestManager(this@MainActivity).GetAllQuotes(listener)
        dialog = ProgressDialog(this@MainActivity)
        dialog?.setTitle("Loading ...")
        dialog?.show()
    }

    private var listener: QuotesResponseListener = object : QuotesResponseListener{
        override fun didFatch(response: List<QuotesResponse>, message: String) {
            dialog?.dismiss()
            recycler_home.setHasFixedSize(true)
            recycler_home.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            val adapter = QuotesListAdapter(this@MainActivity, response, copyListener)
            recycler_home.adapter = adapter
        }

        override fun didError(message: String) {
            dialog?.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }

    }

    private val copyListener: CopyListener = object : CopyListener{
        override fun onCopyClicked(text: String) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("copied_data", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@MainActivity, "Copied in Clicpboard", Toast.LENGTH_LONG).show()
        }

    }

}