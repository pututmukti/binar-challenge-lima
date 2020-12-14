package com.binardev.fragmentchellange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binardev.fragmentchellange.ui.main.HomeFragment

class MainActivity : AppCompatActivity() {

    private var name: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
        }
    }
}
