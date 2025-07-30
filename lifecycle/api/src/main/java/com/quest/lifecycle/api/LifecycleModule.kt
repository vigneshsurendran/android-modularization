package com.quest.lifecycle.api

import android.app.Application
import com.quest.share.api.SharingActivityHolder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LifecycleModule {

    @Binds
    fun activityLifecycleCallbacks(activityLifecycleCallbacks: ActivityLifecycleCallbacks): Application.ActivityLifecycleCallbacks

    @Binds
    fun sharingActivityHolder(activityLifecycleCallbacks: ActivityLifecycleCallbacks): SharingActivityHolder
}