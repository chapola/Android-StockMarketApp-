package com.android.stockmarketapp.ui.theme

import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Green,
    background = DarkBlue,
    onPrimary = Color.DarkGray,
    onBackground = TextWhite
)

@Composable
fun StockMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable ()-> Unit
){
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shape,
        content = content
    )


}