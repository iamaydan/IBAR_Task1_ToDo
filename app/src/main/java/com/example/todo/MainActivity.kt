package com.example.todo

import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val listItems = arrayListOf<String>()
        arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, listItems)
        listView.adapter = arrayAdapter

        add.setOnClickListener {
            listItems.add(editText.text.toString())
            arrayAdapter.notifyDataSetChanged()
            editText.text.clear()
        }

        listView.setOnItemClickListener { adapterView, view, i, j ->

            arrayAdapter.notifyDataSetChanged()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    arrayAdapter.remove(listItems.get(item))
                }
                item--
            }
            position.clear()
            arrayAdapter.notifyDataSetChanged()
        }

    }
}


