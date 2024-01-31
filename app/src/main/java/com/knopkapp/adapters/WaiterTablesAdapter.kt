package com.knopkapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knopkapp.databinding.ItemTablesBinding
import com.knopkapp.models.Tables

class WaiterTablesAdapter(val list: ArrayList<Tables>) : RecyclerView.Adapter<WaiterTablesAdapter.Vh>() {

    inner class Vh(var itemTablesBinding: ItemTablesBinding) : RecyclerView.ViewHolder(itemTablesBinding.root) {
        fun onBind(tables: Tables, position: Int) {
            itemTablesBinding.tablesNumber.text = tables.table
            itemTablesBinding.typeOfWorker.text = tables.waiterType
            itemTablesBinding.nameAndSurnameTextView.text = tables.nameOfWaiter
            itemTablesBinding.commentTextView.text = tables.comment
            itemTablesBinding.timeCount.text = tables.time
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemTablesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

}