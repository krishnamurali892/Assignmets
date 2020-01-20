package com.murali.telstraassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.murali.telstraassignment.R
import com.murali.telstraassignment.databinding.ActivityListBinding
import com.murali.telstraassignment.model.Item
import com.murali.telstraassignment.model.ItemList
import com.murali.telstraassignment.utils.ResponseStatus
import com.murali.telstraassignment.utils.isActiveNetworkAvailable
import com.murali.telstraassignment.utils.toast
import com.murali.telstraassignment.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    lateinit var listViewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        fetchData(false)

        listViewModel.itemList.observe(this, Observer {
            setData(it)
        })

        swipeToRefresh.setOnRefreshListener {
            fetchData(true)
        }
    }

    private fun fetchData(onRefresh: Boolean){
        if(isActiveNetworkAvailable()) {
            listViewModel.loadItemList(onRefresh)
        }else{
            dismissLoader()
            showResponseStatusMessage(ResponseStatus.No_Network)
        }
    }

    private fun setData(itemList: ItemList){
        binding.listViewModel = listViewModel
        dismissLoader()
        setAdapter(itemList.items)
        showResponseStatusMessage(ResponseStatus.valueOf(itemList.responseStatus))
    }

    private fun showResponseStatusMessage(responseStatus: ResponseStatus){
        when(responseStatus){
            ResponseStatus.Success -> toast(responseStatus.messsage)
            ResponseStatus.Fail -> toast(responseStatus.messsage)
            ResponseStatus.No_Network -> toast(responseStatus.messsage)
        }
    }

    private fun setAdapter(items: List<Item>) {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = ListAdapter(this@ListActivity, items)
        }
    }

    private fun dismissLoader(){
        if(swipeToRefresh.isRefreshing){
            swipeToRefresh.isRefreshing = false
        }
        progressDialog.visibility = View.GONE
    }
}
