package com.lordsam.mykart.utility

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class MKEditText(ctx : Context, attrs : AttributeSet) : AppCompatEditText(ctx, attrs) {

    init {
        applyFont()
    }

    private fun applyFont() {

        val typeFace: Typeface = Typeface.createFromAsset(context.assets, "Montserrat-Regular.ttf")
        typeface = typeFace
    }
}