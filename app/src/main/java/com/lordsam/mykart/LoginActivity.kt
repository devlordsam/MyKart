package com.lordsam.mykart

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.lordsam.mykart.utility.MSBTextView

class LoginActivity : AppCompatActivity() {

    private lateinit var txtReg :MSBTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        txtReg = findViewById(R.id.tv_register)

        txtReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}