package com.murali.telstraassignment.model

import retrofit2.Call
import retrofit2.http.GET

interface ListDataService {

    @GET("facts.json")
    fun getItemList(): Call<ItemList>
}