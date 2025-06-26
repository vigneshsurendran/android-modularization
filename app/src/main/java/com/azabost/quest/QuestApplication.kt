package com.azabost.quest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class QuestApplication : Application() {

    @Inject
    lateinit var activityLifecycleCallbacks: ActivityLifecycleCallbacks

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}