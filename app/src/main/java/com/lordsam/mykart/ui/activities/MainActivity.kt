package com.lordsam.mykart.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.TextView
import com.google.firebase.analytics.FirebaseAnalytics
import com.lordsam.mykart.R
import com.lordsam.mykart.utility.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics : FirebaseAnalytics
    private lateinit var tvMain :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sp = getSharedPreferences(Constants.MY_KART_PREF, Context.MODE_PRIVATE)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        tvMain = findViewById(R.id.tvMain)
        val userName = sp.getString(Constants.LOGGED_USER, "")!!
        tvMain.text = "Hello $userName"
    }
}