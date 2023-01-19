package com.bspwnd.apichucknorris

import com.google.gson.annotations.SerializedName

data class Joke (
    val categories: List<String>,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("icon_url") val iconURL: String,
    val id: String,
    @SerializedName("updated_at") val updatedAt: String,
    val url: String,
    val value: String
    )