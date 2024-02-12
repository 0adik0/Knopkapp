package com.knopkapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knopkapp.databinding.ItemDivisionTablesBinding
import com.knopkapp.models.WaiterDates


class DivisionTablesAdapter(val list: ArrayList<WaiterDates>) : RecyclerView.Adapter<DivisionTablesAdapter.Vh>() {

    inner class Vh(var itemDivisionTablesBinding: ItemDivisionTablesBinding) : RecyclerView.ViewHolder(itemDivisionTablesBinding.root) {

        fun onBind(waiterDates: WaiterDates, position: Int) {
            itemDivisionTablesBinding.nameAndSurnameTextView.text = waiterDates.name
            itemDivisionTablesBinding.tablesNumber.text = waiterDates.tables.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemDivisionTablesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

}