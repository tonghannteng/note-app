package com.tengtonghann.android.mynote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * TONGHANN TENG
 * 03/24/2021
 */
@ExperimentalCoroutinesApi
@HiltAndroidApp
class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}