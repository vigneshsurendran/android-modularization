package com.azabost.quest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.quest.lifecycle.api.ActivityLifecycleCallbacks as QuestActivityLifecycleCallbacks

@HiltAndroidApp
class QuestApplication : Application() {

    @Inject
    lateinit var activityLifecycleCallbacks: QuestActivityLifecycleCallbacks

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}