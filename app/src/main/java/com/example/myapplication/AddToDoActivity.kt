package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.utils.Constants
import com.example.myapplication.DAL.DataBaseHandler
import com.example.myapplication.utils.showToast

import kotlinx.android.synthetic.main.activity_add_to_do.*

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)
        setSupportActionBar(toolbar)

        val btnAddToDo = findViewById<Button>(R.id.btnAddTodo)
        val txtToDo = findViewById<EditText>(R.id.txtToDo)
        txtToDo.requestFocus()

        btnAddToDo.setOnClickListener {

            when (txtToDo.text.toString().length) {
                0 -> {
                    txtToDo.error = Constants.CANT_EMPTY
                    txtToDo.requestFocus()
                }
                in 1..4 -> {
                    txtToDo.error = Constants.ERROR_MSG
                    txtToDo.requestFocus()
                }
                else -> {
                    var obj = DataBaseHandler(this)
                    if (obj.addToDo(txtToDo.text.trim().toString())) {
                        this.showToast(Constants.TASK_ADDED)
                        txtToDo.text.clear()
                        txtToDo.requestFocus()
                    } else
                        this.showToast(Constants.WENT_WRONG)
                }
            }
        }
    }
}
