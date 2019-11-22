package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.ToDoModel

class ToDoAdapter(val todoList: ArrayList<ToDoModel>) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tood_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelData: ToDoModel = todoList[position]
        holder.txtTodoView?.text = modelData.todo
        holder.txtTodoIsDone?.text = modelData.isDone.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTodoView = itemView.findViewById<TextView>(R.id.lblTodo)
        val txtTodoIsDone = itemView.findViewById<TextView>(R.id.lblID)
    }
}