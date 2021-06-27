package com.lordsam.mykart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lordsam.mykart.modals.User
import com.lordsam.mykart.utility.Constants
import com.lordsam.mykart.utility.MKEditText

class UserProfileActivity : AppCompatActivity() {

    private lateinit var et_first_name : MKEditText
    private lateinit var et_last_name : MKEditText
    private lateinit var et_email : MKEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        et_first_name = findViewById(R.id.et_first_name_up)
        et_last_name = findViewById(R.id.et_last_name_up)
        et_email = findViewById(R.id.et_email_up)

        var userDetails: User = User()
        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            // Get the user details from intent as a ParcelableExtra.
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }


        et_first_name.isEnabled = false
        et_first_name.setText(userDetails.firstName)

        et_last_name.isEnabled = false
        et_last_name.setText(userDetails.lastName)

        et_email.isEnabled = false
        et_email.setText(userDetails.email)
    }
}