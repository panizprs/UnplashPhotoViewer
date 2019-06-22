package com.acm.workshop.unplashphotoviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.home.HomeFragment
import com.squareup.picasso.Picasso
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file1 = File(filesDir, "myfileMAin.txt")
        file1.writeText("This is my text")



        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, HomeFragment())
            .commit()


    }
}
