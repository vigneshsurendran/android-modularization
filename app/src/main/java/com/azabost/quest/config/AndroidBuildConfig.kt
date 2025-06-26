package com.azabost.quest.config

import com.azabost.quest.BuildConfig
import javax.inject.Inject

class AndroidBuildConfig @Inject constructor(): Config {
    override val isDebug: Boolean = BuildConfig.DEBUG
}