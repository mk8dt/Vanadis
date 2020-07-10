package com.mk8.vanadis.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mk8.vanadis.R
import com.mk8.vanadis.databinding.BaseLayoutBinding
import com.mk8.vanadis.extension.gone
import com.mk8.vanadis.extension.visible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_layout)
    }
}