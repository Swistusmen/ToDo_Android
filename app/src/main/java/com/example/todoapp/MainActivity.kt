package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemlist= arrayListOf<String>()
        var adapter= ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice, itemlist)

        val clear=findViewById<Button>(R.id.clear)
        val add=findViewById<Button>(R.id.add_button)
        val delete=findViewById<Button>(R.id.delete)
        val editText=findViewById<EditText>(R.id.editText)
        val listView=findViewById<ListView>(R.id.listView)

        add.setOnClickListener{
            itemlist.add(editText.text.toString())
            listView.adapter=adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        delete.setOnClickListener{
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count= listView.count
            var item= count-1
            while(item>=0){
                if(position.get(item)){
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        clear.setOnClickListener{
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "You Selected the item --> "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }
    }


}