package com.cnm.todo.view.main

import android.content.Context
import androidx.lifecycle.LiveData
import com.cnm.todo.db.entity.TodoEntity
import com.cnm.todo.db.repository.TodoRepository

class MainPresenter(context: Context) {
    private val repository: TodoRepository by lazy {
        TodoRepository(context)
    }

    private val todos: LiveData<List<TodoEntity>> by lazy {
        repository.getTodo()
    }

    fun getTodo() = todos



}