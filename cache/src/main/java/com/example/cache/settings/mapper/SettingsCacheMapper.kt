package com.example.cache.settings.mapper

import com.example.data.settings.models.ThemeType

interface SettingsCacheMapper {

    fun mapToCache(themeType: ThemeType) : String

}