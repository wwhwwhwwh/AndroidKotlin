package com.zl.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ThemeActivity :AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         initTheme();
        super.onCreate(savedInstanceState)
    }

    open fun initTheme() {};

}