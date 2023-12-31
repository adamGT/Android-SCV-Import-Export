package com.goldlepre.android_scv_import_export.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldlepre.android_scv_import_export.R
import com.goldlepre.android_scv_import_export.models.UserData

class DetailListAdapter : RecyclerView.Adapter<DetailListAdapter.ListViewModel> () {
    private var dataList: List<UserData> = listOf()

    class ListViewModel(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.title)
        val email: TextView = itemView.findViewById(R.id.owner)
        val phoneNumber: TextView = itemView.findViewById(R.id.phone)
    }

    fun updateList(newDataList: List<UserData>){
        dataList = newDataList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.detail_list_item, parent, false)

        return ListViewModel(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListViewModel, position: Int) {
        val data =dataList[position]
        holder.name.text = data.name
        holder.email.text = data.email
        holder.phoneNumber.text = data.phoneNumber
    }
}