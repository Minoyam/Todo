package com.cnm.todo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var content: String = "",
    var date: Long = 0

)