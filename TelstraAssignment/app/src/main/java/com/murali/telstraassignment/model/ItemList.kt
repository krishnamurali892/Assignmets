package com.murali.telstraassignment.model

import com.google.gson.annotations.SerializedName

data class ItemList(
    var responseStatus: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("rows")
    val items: List<Item>
)