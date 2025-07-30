package com.azabost.quest

import com.quest.config.api.Config
import javax.inject.Inject

class AndroidBuildConfig @Inject constructor() : Config {
    override val isDebug: Boolean = BuildConfig.DEBUG
}