package com.littleapp.countries.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.littleapp.countries.databinding.ActivitySplashBinding
import com.littleapp.countries.utils.THEME
import com.littleapp.countries.utils.launchDelayed
import com.littleapp.countries.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        THEME.setThemeOfApp(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        launchDelayed(TIME_PER_MILLIS.toLong()) {
            openActivity(MainActivity::class.java, finish = true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val TIME_PER_MILLIS = 1000
    }
}