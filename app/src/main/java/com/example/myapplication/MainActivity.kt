package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DAL.DataBaseHandler
import com.example.myapplication.Models.ToDoModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bindDataToRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.addToDo -> {
                return handleAddToDoMenuClickEvent()
            }
            else -> {
                // Just have some false block code - that's all
                return false
            }
        }
    }

    private fun handleAddToDoMenuClickEvent(): Boolean {
        val addToDoIntent = Intent(this, AddToDoActivity::class.java)
        startActivity(addToDoIntent)
        return true
    }

    override fun onRestart() {
        super.onRestart()
        bindDataToRecyclerView()
    }

    private fun bindDataToRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rcylrToDo)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        try {
            val readData = DataBaseHandler(this)
            val toDoData = readData.getAllToDo()
            recyclerView.adapter = ToDoAdapter(toDoData)

        } catch (ex: Exception) {
            System.out.println(ex.message)
            throw ex
        }
    }
}
