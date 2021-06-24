package com.lordsam.mykart.utility

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

class MKButton(ctx : Context, attrs : AttributeSet) : AppCompatButton(ctx, attrs) {

    init {
        applyFont()
    }

    private fun applyFont(){

        val typeFace : Typeface =  Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        typeface = typeFace
    }
}