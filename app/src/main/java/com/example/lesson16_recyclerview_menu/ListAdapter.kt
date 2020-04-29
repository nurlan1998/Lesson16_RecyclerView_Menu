package com.example.lesson16_recyclerview_menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter(val activity: MainActivity) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var model: List<Contact> = listOf()

    fun setData(data : List<Contact>){
        model = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(model[position],itemCount,position,activity)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun populateModel(contact: Contact, size: Int, position: Int, activity: MainActivity){
            itemView.tvName.text = contact.name
            itemView.tvLastName.text = contact.lastName
            itemView.setOnClickListener {
                activity.onItemClicked(size,position)
            }
            itemView.btnOptions.setOnClickListener {
                activity.onOptionsButtonClick(itemView.btnOptions,size,position)
            }
        }

    }
}