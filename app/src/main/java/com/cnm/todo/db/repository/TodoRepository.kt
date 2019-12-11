package com.cnm.todo.db.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.cnm.todo.db.TodoDatabase
import com.cnm.todo.db.dao.TodoDao
import com.cnm.todo.db.entity.TodoEntity
import io.reactivex.Observable

class TodoRepository(context: Context) {

    private val todoDao: TodoDao by lazy {
        val db = TodoDatabase.getInstance(context)!!
        db.todoDao()
    }
    private val todos: LiveData<List<TodoEntity>> by lazy {
        todoDao.getTodo()
    }

    fun getTodo(): LiveData<List<TodoEntity>> = todos

    fun getTodoById(id: Long): LiveData<TodoEntity> = todoDao.getTodoById(id)

    fun insert(todoEntity: TodoEntity): Observable<Unit> =
        Observable.fromCallable { todoDao.insertTodo(todoEntity) }

    fun delete(id: Long): Observable<Unit> = Observable.fromCallable { todoDao.deleteById(id) }


}