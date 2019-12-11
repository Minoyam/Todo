package com.cnm.todo.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cnm.todo.R
import com.cnm.todo.db.entity.TodoEntity
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val list: List<TodoEntity>, val click: (Long) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val item = list[adapterPosition]
                click(item.id)
            }
        }
        fun bind(item: TodoEntity) {
            with(itemView) {
                tv_todo.text = item.content
                tv_date.text = DateFormat.format("yyyy/MM/dd", item.date)
            }
        }
    }

}