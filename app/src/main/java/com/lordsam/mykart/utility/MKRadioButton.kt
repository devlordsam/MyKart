package com.lordsam.mykart.utility

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

class MKRadioButton (context: Context, attrs: AttributeSet) :
    AppCompatRadioButton(context, attrs) {

    init {
        applyFont()
    }

    /**
     * Applies a font to a Radio Button.
     */
    private fun applyFont() {

        val typeface: Typeface =
            Typeface.createFromAsset(context.assets, "Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}