package com.example.cache.settings.source

import com.example.cache.settings.mapper.SettingsCacheMapper
import com.example.data.settings.models.ThemeType

class SettingsCacheMapperImpl : SettingsCacheMapper {

    override fun mapToCache(
        themeType: ThemeType
    ): String {
        return themeType.name
    }

}