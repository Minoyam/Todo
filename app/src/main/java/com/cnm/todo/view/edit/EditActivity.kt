package com.cnm.todo.view.edit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cnm.todo.R
import com.cnm.todo.db.entity.TodoEntity
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private lateinit var editPresenter: EditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editPresenter = EditPresenter(this)
        val id = intent.getLongExtra("id", -1L)
        if (id == -1L) {
            insertMode()
        } else {
            updateMode(id)
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

    }

    private fun updateMode(id: Long) {
        bt_cancel.visibility = View.VISIBLE
        editPresenter.getTodoById(id).observe(this, Observer { todo ->
            todo?.let {
                et_body.setText(todo.content)
                calendarView.date = todo.date
            }
        })
        bt_done.setOnClickListener { updateTodo(id) }
        bt_cancel.setOnClickListener { deleteTodo(id) }
    }

    private fun deleteTodo(id: Long) {
        editPresenter.deleteById(id) { finish() }
    }

    private fun updateTodo(id: Long) {
        val todo = TodoEntity(id, et_body.text.toString(), calendar.timeInMillis)
        editPresenter.insertTodo(todo) { finish() }
    }

    private fun insertMode() {
        bt_cancel.visibility = View.GONE
        bt_done.setOnClickListener {
            insertTodo()
        }
    }

    private fun insertTodo() {
        val todo = TodoEntity(0, et_body.text.toString(), calendar.timeInMillis)
        editPresenter.insertTodo(todo) { finish() }
    }
}
