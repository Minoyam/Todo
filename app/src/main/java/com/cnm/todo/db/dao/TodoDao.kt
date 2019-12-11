package com.cnm.todo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cnm.todo.db.entity.TodoEntity

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodo(todoEntity: TodoEntity)

    @Query("DELETE  FROM todo WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Long): LiveData<TodoEntity>

    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun getTodo(): LiveData<List<TodoEntity>>
}