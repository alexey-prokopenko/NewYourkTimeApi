package com.example.new_yourk_times_api.application

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private lateinit var appInstance: App

        fun applicationContext(): Context {
            return appInstance.applicationContext
        }
    }
}