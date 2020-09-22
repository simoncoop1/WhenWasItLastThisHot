package com.example.whenwasitlastthishot.ui.main

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.whenwasitlastthishot.R
import com.example.whenwasitlastthishot.weather
import org.json.JSONArray
import java.io.*
import java.net.URL
import java.text.MessageFormat
import java.util.*


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
        val tab = arguments?.getInt(ARG_SECTION_NUMBER)
        if ( tab == 1) {
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
                    buttonClick(root)
                    return@OnEditorActionListener true
                }
                false
            })
            val bu = root.findViewById<Button>(R.id.button)
            bu.setOnClickListener {
                // Do something in response to button click
                buttonClick(root)
            }

            val autofill = root.findViewById<Button>(R.id.button2)
            autofill.setOnClickListener {

                if (Build.VERSION.SDK_INT > 9) {
                    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policy)
                }

                // json web request

                val a = context?.resources?.getString(R.string.owm)

                val url = "http://api.openweathermap.org/data/2.5/weather?q=southam,warwickshire,uk&appid=$a&units=metric"
                val k = url.saveToString()
                val obj = JSONArray(k)

                //val k = "http://10.0.2.2:3003/url".saveToString()
                Log.i("myLog", obj.toString())
                //tE.setText(k.toString())
                buttonClick(root)
            }

            return root
        }
        else if(tab == 2){
            val root = inflater.inflate(R.layout.cold_fragment_, container, false)
            val textView: TextView = root.findViewById(R.id.section_label)
            pageViewModel.text.observe(this, Observer<String> {
                textView.text = it
            })
            val tE = root.findViewById<EditText>(R.id.editTextTemp)
            Log.i("myLog", tE.toString())
            tE.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.i("myLog", "Here you can write the code")
                    coldButtonClick(root)
                    return@OnEditorActionListener true
                }
                false
            })
            val bu = root.findViewById<Button>(R.id.button)
            bu.setOnClickListener {
                // Do something in response to button click
                coldButtonClick(root)
            }
            return root
        }
        else{
            val root = inflater.inflate(R.layout.about_fragment_, container, false)
            val textView: TextView = root.findViewById(R.id.section_label)
            pageViewModel.text.observe(this, Observer<String> {
                textView.text = it
            })

            //api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}
            //http://api.openweathermap.org/data/2.5/weather?q=coventry&appid={key}&units=metric
            /*val url = "http://my-json-feed"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    textView.text = "Response: %s".format(response.toString())
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
            )

            Access the RequestQueue through your singleton class.
                        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        */

            return root
        }
    }



    fun String.saveToFile(path: String) {
        URL(this).openStream().use { input ->
            FileOutputStream(File(path)).use { output ->
                input.copyTo(output)
            }
        }
    }

    fun String.saveToString():String {

        var k = ""
        k += " "

        URL(this).openStream().use { input ->
            ByteArrayOutputStream().use { output ->
                input.copyTo(output)
                return output.toString()
            }
        }

        return ""
    }

    fun getDaySuffix(day: Int): String?{
        return if(day == 1 || day == 21 || day == 31)
            context?.resources?.getString(R.string.daySuffix_st)
        else if(day == 2 || day == 22) {
            context?.resources?.getString(R.string.daySuffix_nd)
        } else
            context?.resources?.getString(R.string.daySuffix_th)
    }

    fun buttonClick(root: View){
        Log.i("myLog", "buttonClick method")
        val tv = root.findViewById<TextView>(R.id.textView)
        val rawResource  = getResources().openRawResource(R.raw.temperature);
        val r = BufferedReader(InputStreamReader(rawResource))
        val allText = rawResource.bufferedReader().use(BufferedReader::readText)
        val obj = JSONArray(allText)
        val v = weather(obj)
        val tE = root.findViewById<EditText>(R.id.editTextTemp)
        if(tE.text.isEmpty())//empty field case
            return
        val input = tE.text.toString().toFloat()
        val aDate = v.GetMostRecentThisHot(input)
        val result = MessageFormat.format(
            context?.resources?.getString(R.string.temp),
            aDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()),
            aDate.get(Calendar.DAY_OF_MONTH),
            getDaySuffix(aDate.get(Calendar.DAY_OF_MONTH)),
            aDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
            aDate.get(Calendar.YEAR).toString()
        );
        tv.text = result
    }

    fun coldButtonClick(root: View){
        Log.i("myLog", "Button. Here you can write the code")
        val tv = root.findViewById<TextView>(R.id.textView)
        val rawResource  = getResources().openRawResource(R.raw.temperature);
        val r = BufferedReader(InputStreamReader(rawResource))
        val allText = rawResource.bufferedReader().use(BufferedReader::readText)
        val obj = JSONArray(allText)
        val v = weather(obj)
        val tE = root.findViewById<EditText>(R.id.editTextTemp)
        val input = tE.text.toString().toFloat()
        val aDate = v.GetMostRecentThisCold(input)
        val result = MessageFormat.format(
            context?.resources?.getString(R.string.temp),
            aDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()),
            aDate.get(Calendar.DAY_OF_MONTH),
            getDaySuffix(aDate.get(Calendar.DAY_OF_MONTH)),
            aDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
            aDate.get(Calendar.YEAR).toString()
        );
        tv.text = result
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