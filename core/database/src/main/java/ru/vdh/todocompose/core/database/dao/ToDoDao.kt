package ru.vdh.todocompose.core.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.vdh.todocompose.core.database.model.ToDoTaskDatabaseModel

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks(): Flow<List<ToDoTaskDatabaseModel>>

    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTaskDatabaseModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: ToDoTaskDatabaseModel)

    @Update
    suspend fun updateTask(toDoTask: ToDoTaskDatabaseModel)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTaskDatabaseModel)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskDatabaseModel>>

    @Query(
        """
        SELECT * FROM todo_table ORDER BY
    CASE
        WHEN priority LIKE 'L%' THEN 1
        WHEN priority LIKE 'M%' THEN 2
        WHEN priority LIKE 'H%' THEN 3
    END
    """
    )
    fun sortByLowPriority(): Flow<List<ToDoTaskDatabaseModel>>

    @Query(
        """
        SELECT * FROM todo_table ORDER BY
    CASE
        WHEN priority LIKE 'H%' THEN 1
        WHEN priority LIKE 'M%' THEN 2
        WHEN priority LIKE 'L%' THEN 3
    END
    """
    )
    fun sortByHighPriority(): Flow<List<ToDoTaskDatabaseModel>>
}