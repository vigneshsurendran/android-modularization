package com.quest.share.impl

import com.quest.share.api.ShareSender
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ShareModule {

    @Binds
    fun shareSender(shareSenderImpl: ShareSenderImpl): ShareSender
}