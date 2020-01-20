package com.murali.telstraassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.murali.telstraassignment.model.Item
import com.murali.telstraassignment.model.ItemList
import com.murali.telstraassignment.model.ListDataService
import com.murali.telstraassignment.model.RetrofitClient
import com.murali.telstraassignment.utils.ResponseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel: ViewModel(){

    var itemList =  MutableLiveData<ItemList>()

    fun loadItemList(onRefresh: Boolean) {
        if (onRefresh || itemList.value == null) {
            val listDataService = RetrofitClient.buildService(ListDataService::class.java)
            val requestCall = listDataService.getItemList()
            requestCall.enqueue(object : Callback<ItemList> {
                override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                    if (response.isSuccessful) {
                        response.body().responseStatus = ResponseStatus.Success.name
                        itemList.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ItemList>, t: Throwable) {
                    itemList.value = ItemList(ResponseStatus.Fail.name, "", listOf<Item>())
                }
            })
        }
    }

}