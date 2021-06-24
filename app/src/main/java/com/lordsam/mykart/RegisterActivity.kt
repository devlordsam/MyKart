package com.lordsam.mykart

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toolbar
import androidx.appcompat.widget.AppCompatCheckBox
import com.lordsam.mykart.utility.MKButton
import com.lordsam.mykart.utility.MKEditText
import com.lordsam.mykart.utility.MSBTextView

class RegisterActivity : BaseActivity() {

    private lateinit var btn_register :MKButton
    private lateinit var txtLogin :MSBTextView
    private lateinit var tbr :androidx.appcompat.widget.Toolbar
    private lateinit var et_first_name :MKEditText
    private lateinit var et_last_name :MKEditText
    private lateinit var et_password :MKEditText
    private lateinit var et_confirm_password :MKEditText
    private lateinit var et_email :MKEditText
    private lateinit var cb_terms_and_condition :AppCompatCheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        btn_register = findViewById(R.id.btn_register)
        txtLogin = findViewById(R.id.tv_login)
        tbr = findViewById(R.id.toolbar_register_activity)
        et_first_name = findViewById(R.id.et_first_name)
        et_last_name = findViewById(R.id.et_last_name)
        et_password = findViewById(R.id.et_password)
        et_confirm_password = findViewById(R.id.et_confirm_password)
        et_email = findViewById(R.id.et_email)
        cb_terms_and_condition = findViewById(R.id.cb_terms_and_condition)

        txtLogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_register.setOnClickListener {
            validateRegisterDetails()
        }

        setUpActionBar()
    }

    private fun setUpActionBar(){

        setSupportActionBar(tbr)

        val ab = supportActionBar
        if (ab != null){
            ab.setDisplayHomeAsUpEnabled(true)
            ab.setHomeAsUpIndicator(R.drawable.back_arrow)
        }
        tbr.setNavigationOnClickListener{ onBackPressed() }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_first_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(et_last_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(et_confirm_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            et_password.text.toString().trim { it <= ' ' } != et_confirm_password.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
            !cb_terms_and_condition.isChecked -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid.", false)
                true
            }
        }
    }
}