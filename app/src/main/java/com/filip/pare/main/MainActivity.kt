package com.filip.pare.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.filip.pare.R
import com.filip.pare.setup.SetupFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Don't recreate fragments on orientation change
        if (savedInstanceState !== null)
            return
        supportFragmentManager
            .beginTransaction()
            .add(R.id.mainContainer, SetupFragment())
            .commit()
    }
}