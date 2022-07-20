package com.example.madplayground.ui.config

import androidx.compose.runtime.compositionLocalOf

data class WindowConfiguration(
    val windowWidthType: WindowWidthType = WindowWidthType.COMPACT,
    val windowHeightType: WindowHeightType = WindowHeightType.MEDIUM,
) {

    @Suppress("MemberVisibilityCanBePrivate")
    val combinedWindowType: CombinedWindowType =
        when (windowWidthType to windowHeightType) {

            WindowWidthType.COMPACT to WindowHeightType.COMPACT  ->
                CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT

            WindowWidthType.COMPACT to WindowHeightType.MEDIUM   ->
                CombinedWindowType.COMPACT_WIDTH_MEDIUM_HEIGHT

            WindowWidthType.COMPACT to WindowHeightType.EXPANDED ->
                CombinedWindowType.COMPACT_WIDTH_EXPANDED_HEIGHT

            WindowWidthType.MEDIUM to WindowHeightType.COMPACT   ->
                CombinedWindowType.MEDIUM_WIDTH_COMPACT_HEIGHT

            WindowWidthType.MEDIUM to WindowHeightType.MEDIUM    ->
                CombinedWindowType.MEDIUM_WIDTH_MEDIUM_HEIGHT

            WindowWidthType.MEDIUM to WindowHeightType.EXPANDED  ->
                CombinedWindowType.MEDIUM_WIDTH_EXPANDED_HEIGHT

            WindowWidthType.EXPANDED to WindowHeightType.COMPACT ->
                CombinedWindowType.EXPANDED_WIDTH_COMPACT_HEIGHT

            WindowWidthType.EXPANDED to WindowHeightType.MEDIUM  ->
                CombinedWindowType.EXPANDED_WIDTH_MEDIUM_HEIGHT

            else                                                 ->
                CombinedWindowType.EXPANDED_WIDTH_EXPANDED_HEIGHT


        }

}

enum class WindowWidthType {

    /** Represents the majority of phones in landscape */
    COMPACT,

    /** Represents the majority of tablets in landscape and majority of phones in portrait */
    MEDIUM,

    /** Represents the majority of tablets in portrait */
    EXPANDED;
}

enum class WindowHeightType {

    /** Represents the majority of phones in landscape */
    COMPACT,

    /** Represents the majority of tablets in landscape and majority of phones in portrait */
    MEDIUM,

    /** Represents the majority of tablets in portrait */
    EXPANDED;
}

enum class CombinedWindowType {

    /**
     * Very small window, probably in multi window mode.
     */
    COMPACT_WIDTH_COMPACT_HEIGHT,

    /**
     * Typical cell phone in portrait
     */
    COMPACT_WIDTH_MEDIUM_HEIGHT,

    /**
     * Strange very tall device
     */
    COMPACT_WIDTH_EXPANDED_HEIGHT,

    /**
     * Typical cell phone in landscape
     */
    MEDIUM_WIDTH_COMPACT_HEIGHT,

    /**
     * Probably on desktop or tablet
     */
    MEDIUM_WIDTH_MEDIUM_HEIGHT,

    /**
     * Typical tablet
     */
    MEDIUM_WIDTH_EXPANDED_HEIGHT,

    /**
     * Strange very wide device
     */
    EXPANDED_WIDTH_COMPACT_HEIGHT,

    /**
     * Desktop or large tablet
     */
    EXPANDED_WIDTH_MEDIUM_HEIGHT,

    /**
     * Desktop or large tablet
     */
    EXPANDED_WIDTH_EXPANDED_HEIGHT;

}

val LocalWindowConfiguration = compositionLocalOf {
    WindowConfiguration()
}
