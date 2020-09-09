package com.example.whenwasitlastthishot

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.whenwasitlastthishot.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val asection = sectionsPagerAdapter.getItem(0)

        //val k = resources.getStringArray(R.array.planets_array)
        //val json = "[[\"a\",\"b\",\"c\"],\"d\"]"
        val rawResource  = getResources().openRawResource(R.raw.temperature);
        val r = BufferedReader(InputStreamReader(rawResource))
        val allText = rawResource.bufferedReader().use(BufferedReader::readText)

        val obj = JSONArray(allText)
        Log.d("MyLog", obj.toString());
        Log.d("MyLog", (obj[0] as JSONArray)[2].toString());

        //Log.i("myLog", foos.toString())
        val even: Array<Array<String?>> = arrayOf(arrayOf("2", "4"), arrayOf("2", "4"))
    }
}
