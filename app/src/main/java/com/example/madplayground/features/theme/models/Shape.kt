package com.example.madplayground.features.theme.models

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val RoundedShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val CutShapes = Shapes(
    small = CutCornerShape(4.dp),
    medium = CutCornerShape(4.dp),
    large = CutCornerShape(0.dp)
)