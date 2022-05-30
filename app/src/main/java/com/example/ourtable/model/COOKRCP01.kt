package com.example.ourtable.model


import com.google.gson.annotations.SerializedName

data class COOKRCP01(
    @SerializedName("row")
    val row: List<Row>?
)