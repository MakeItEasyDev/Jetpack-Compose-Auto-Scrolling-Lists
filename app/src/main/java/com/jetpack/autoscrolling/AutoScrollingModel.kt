package com.jetpack.autoscrolling

import androidx.annotation.DrawableRes

data class AutoScrollingModel(
    @DrawableRes val iconResource: Int,
    val contentDescription: String
)

val autoScrollingList = listOf(
    AutoScrollingModel(R.drawable.apple, "Apple"),
    AutoScrollingModel(R.drawable.banana, "Banana"),
    AutoScrollingModel(R.drawable.cherries, "Cherries"),
    AutoScrollingModel(R.drawable.dates, "Dates"),
    AutoScrollingModel(R.drawable.eggfruit, "Egg Fruit"),
    AutoScrollingModel(R.drawable.fig, "Fig"),
    AutoScrollingModel(R.drawable.grapes, "Grapes"),
    AutoScrollingModel(R.drawable.hackberry, "Hack Berry"),
)