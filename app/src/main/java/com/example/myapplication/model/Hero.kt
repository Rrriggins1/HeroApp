package com.example.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val desc: Int,
    @StringRes val vuln: Int,
    @StringRes val vulndesc: Int
)
