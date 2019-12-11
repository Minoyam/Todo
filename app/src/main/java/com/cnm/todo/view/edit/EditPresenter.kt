package com.cnm.todo.view.edit

import android.content.Context
import androidx.lifecycle.LiveData
import com.cnm.todo.db.entity.TodoEntity
import com.cnm.todo.db.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EditPresenter(context: Context)  {
    private val repository: TodoRepository by lazy {
        TodoRepository(context)
    }

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getTodoById(id: Long): LiveData<TodoEntity> = repository.getTodoById(id)

    fun insertTodo(todoEntity: TodoEntity, next: (dispose : () -> Unit) -> Unit) {
        disposable.add(
            repository.insert(todoEntity).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe { next{dispose()} }
        )
    }

    fun deleteById(id: Long, next: (dispose : () -> Unit) -> Unit) = disposable.add(
        repository.delete(id).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe { next{dispose()} }
    )

    fun dispose(){
        disposable.dispose()

    }

}