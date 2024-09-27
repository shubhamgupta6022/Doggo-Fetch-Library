package com.sgupta.doggofetch.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgupta.doggofetch.R
import com.sgupta.doggofetch.databinding.ActivityMainBinding
import com.sgupta.doggofetch.feature.home.DogHomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, DogHomeFragment.newInstance())
            .commit()
    }
}