package com.example.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.databinding.ItemRvBinding
import com.example.roomdatabase.models.My_Contact

class RvAdapter( var list: ArrayList<My_Contact>, var rvAction: RvAction) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class  Vh( var itemRv: ItemRvBinding):RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(myContact: My_Contact) {
            itemRv. name.text = myContact.name
            itemRv.surname.text = myContact.surname
            itemRv.delete.setOnClickListener {
                rvAction.delete(myContact)
            }
            itemRv.edit.setOnClickListener {
                rvAction.edit(myContact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
    interface RvAction{
        fun delete(myContact: My_Contact)
        fun edit(myContact: My_Contact)
    }
}