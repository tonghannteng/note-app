package com.tengtonghann.android.mynote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tengtonghann.android.mynote.databinding.ActivityMainBinding

/**
 * TONGHANN TENG
 * 03/24/2021
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)
    }
}