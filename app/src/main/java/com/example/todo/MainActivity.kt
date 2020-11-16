package com.example.todo

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val listItems = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, listItems)
        listView.adapter = arrayAdapter


        add.setOnClickListener {
            listItems.add(editText.text.toString())
            arrayAdapter.notifyDataSetChanged()
            editText.text.clear()
            textView2.visibility = View.INVISIBLE
        }

        listView.setOnItemClickListener { adapterView, view, i, j ->
            Toast.makeText(this, "You clicked ${listItems[i]}", Toast.LENGTH_SHORT).show()
            arrayAdapter.notifyDataSetChanged()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    arrayAdapter.remove(listItems[item])
                }
                item--
            }
            position.clear()
            arrayAdapter.notifyDataSetChanged()
        }

        clear.setOnClickListener {
            arrayAdapter.clear()
            textView2.visibility = View.VISIBLE
        }
    }
}


