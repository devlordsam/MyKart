package com.lordsam.mykart

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.lordsam.mykart.utility.MKButton
import com.lordsam.mykart.utility.MKEditText

class ForgotPasswordActivity : BaseActivity() {

    private lateinit var tbr :androidx.appcompat.widget.Toolbar
    private lateinit var et_email :MKEditText
    private lateinit var btn :MKButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        tbr = findViewById(R.id.toolbar_forgot_password_activity)
        et_email = findViewById(R.id.et_email_f)
        btn = findViewById(R.id.btn_submit_f)

        btn.setOnClickListener {
            forgotPass()
        }

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(tbr)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.white_arrow)
        }

        tbr.setNavigationOnClickListener { onBackPressed() }
    }

    private fun forgotPass(){

        val email: String = et_email.text.toString().trim { it <= ' ' }

        if (email.isEmpty()) {
            showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
        } else {

            showProgressDialog(resources.getString(R.string.please_wait))

            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->

                    hideProgressDialog()

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            resources.getString(R.string.email_sent_success),
                            Toast.LENGTH_LONG
                        ).show()

                        finish()
                    } else {
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}