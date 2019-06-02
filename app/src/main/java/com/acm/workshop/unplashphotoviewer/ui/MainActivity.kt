package com.acm.workshop.unplashphotoviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, HomeFragment())
            .commit()
    }
}
