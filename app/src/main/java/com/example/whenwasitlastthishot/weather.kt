package com.example.whenwasitlastthishot

import android.content.res.Resources
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class weather {
    val even: Array<Array<String?>> = arrayOf(arrayOf("1878", "1","66","43","125","67","175","163","187","215","175","141","78","46"),
        arrayOf("1878", "2","74","45","118","71","193","153","158","222","190","136","78","43"),
        arrayOf("1878", "3","87","51","122","75","150","177","180","195","198","171","81","38"),
        arrayOf("1878", "4","86","55","113","106","148","156","202","207","206","172","72","50"),
        arrayOf("1878", "5","75","49","104","100","185","150","208","229","221","210","67","56"),
        arrayOf("1878", "6","78","37","106","113","179","187","231","219","217","190","71","40"),
        arrayOf("1878", "7","56","76","120","122","171","190","210","222","208","178","84","33"),
        arrayOf("1878", "8","48","76","93","127","114","195","215","219","191","180","65","16"),
        arrayOf("1878", "9","35","31","66","97","132","166","202","219","186","155","72","16"),
        arrayOf("1878", "10","40","38","97","97","179","170","175","205","192","166","88","-11"),
        arrayOf("1878", "11","39","63","100","145","168","157","177","208","209","136","71","-6"),
        arrayOf("1878", "12","37","60","97","157","195","161","170","205","183","169","55","-12"))




    fun GetMostRecentThisHot(t : Float): Calendar{
        //work out the date when it was last this hot

        //the date when this last occurred
        val calendar = Calendar.getInstance()
        calendar.set(1750,1,12)

        return calendar
    }

    fun LoadTemps(){
    }
}