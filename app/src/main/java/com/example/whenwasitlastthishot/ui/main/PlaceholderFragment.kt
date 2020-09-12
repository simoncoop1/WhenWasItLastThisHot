package com.example.whenwasitlastthishot.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whenwasitlastthishot.R
import java.text.MessageFormat

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })

        val tE = root.findViewById<EditText>(R.id.editTextTemp)
        Log.i("myLog", tE.toString())
        tE.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.i("myLog", "Here you can write the code")
                val tv = root.findViewById<TextView>(R.id.textView)
                tv.text = context?.resources?.getString(R.string.temp)
                return@OnEditorActionListener true
            }
            false
        })

        val bu = root.findViewById<Button>(R.id.button)
        bu.setOnClickListener {
            // Do something in response to button click
            Log.i("myLog", "Button. Here you can write the code")
            val tv = root.findViewById<TextView>(R.id.textView)
            tv.text = context?.resources?.getString(R.string.temp)
            //"<h1>Hello {0}. Your age is {1,integer}</h1>"
            //val result = MessageFormat.format(theString, name, age);
        }




        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}