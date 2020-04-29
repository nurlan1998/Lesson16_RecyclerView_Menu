package com.example.lesson16_recyclerview_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter: ListAdapter = ListAdapter(this)
    val models: MutableList<Contact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        setData(0, 1)
    }

    fun setData(size: Int, count: Int) {
        for (i in size until count + size) {
            val model = Contact()
            model.name = "Title${i + 1}"
            model.lastName = "Description${i + 1}"
            models.add(model)
        }
        adapter.setData(models)
    }

    fun onItemClicked(size: Int, position: Int) {
        setData(size, position + 1)
    }

    fun addData(size: Int) {
        val model = Contact()
        model.name = "Title${size + 1}"
        model.lastName = "lastName${size + 1}"
        models.add(model)
        adapter.setData(models)
    }
    fun deleteData(pos:Int){
        models.removeAt(pos)
        adapter.notifyDataSetChanged()
    }

    fun onOptionsButtonClick(view: View,mSize:Int,pos:Int) {
        val optionMenu = PopupMenu(this, view)
        val menuInflater = optionMenu.menuInflater
        menuInflater.inflate(R.menu.main_menu, optionMenu.menu)
        optionMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_add ->{
                    addData(mSize)
                }
                R.id.action_delete ->{
                    deleteData(pos)
                }
            }
            return@setOnMenuItemClickListener true
        }
        optionMenu.show()
    }
}
