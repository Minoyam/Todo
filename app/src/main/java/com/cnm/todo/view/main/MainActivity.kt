package com.cnm.todo.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cnm.todo.R
import com.cnm.todo.adapter.TodoAdapter
import com.cnm.todo.db.entity.TodoEntity
import com.cnm.todo.view.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list: ArrayList<TodoEntity> by lazy { arrayListOf<TodoEntity>() }
    private lateinit var presenter: MainPresenter
    private val adapter = TodoAdapter(list, ::editTodo)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_content.adapter = adapter
        presenter = MainPresenter(this)
        presenter.getTodo().observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        bt_add.setOnClickListener { createTodo() }

    }

    fun createTodo() {
        val intent = Intent(this@MainActivity, EditActivity::class.java)
        startActivity(intent)
    }

    fun editTodo(id: Long) {
        val intent = Intent(this@MainActivity, EditActivity::class.java).putExtra("id", id)
        startActivity(intent)
    }


}
