package com.goldlepre.android_scv_import_export.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.goldlepre.android_scv_import_export.R
import com.goldlepre.android_scv_import_export.models.excel.ExcelData

class MainListAdapter: RecyclerView.Adapter<MainListAdapter.ListViewModel> () {
    private var dataList: List<ExcelData> = listOf()
    private var onClickListener: OnClickListener? = null

    class ListViewModel(itemView: View):RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val owner: TextView = itemView.findViewById(R.id.owner)
    }

    fun updateList(newDataList: List<ExcelData>){
        dataList = newDataList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ListViewModel(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, data: ExcelData)
    }

    override fun onBindViewHolder(holder: ListViewModel, position: Int) {
        val data =dataList[position]
        holder.title.text = data.title
        holder.owner.text = data.owner
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, data )
            }
        }
    }
}