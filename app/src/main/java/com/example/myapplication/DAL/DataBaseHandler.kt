package com.example.myapplication.DAL

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.Models.ToDoModel
import com.example.myapplication.utils.Constants

class DataBaseHandler(context: Context) :
    SQLiteOpenHelper(context,
        Constants.DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable =
            "CREATE TABLE ${Constants.TABLE_NAME} (${Constants.ID} INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ", ${Constants.COL_TODO} VARCHAR(50) NOT NULL" +
                    ", ${Constants.COL_ISDONE} INTEGER DEFAULT 0)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${Constants.TABLE_NAME}")
        onCreate(db)
    }

    fun addToDo(todoText: String): Boolean {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(Constants.COL_TODO, todoText)
        var result = db.insert(Constants.TABLE_NAME, null, cv)

        if (result == -1.toLong())
            return false

        return true
    }

    fun getAllToDo(): ArrayList<ToDoModel> {
        val db = this.writableDatabase
        var query =
            "SELECT ${Constants.COL_TODO}, ${Constants.COL_ISDONE} FROM ${Constants.TABLE_NAME} " +
                    "WHERE ${Constants.COL_ISDONE} = 0"
        val cursor = db.rawQuery(query, null)
        var todoData = ArrayList<ToDoModel>()

        if (cursor != null) {
            cursor.moveToFirst()

            while (cursor.moveToNext()) {
                todoData.add(
                    ToDoModel(
                        cursor.getString(cursor.getColumnIndex(Constants.COL_TODO)),
                        getCompletedStatus(cursor.getColumnIndex(Constants.COL_ISDONE))
                    )
                )
            }
        }
        cursor.close()
        return todoData
    }

    private fun getCompletedStatus(status: Int): Char {
        /*
            Meaning for 0 and 1 in isDone column
            0 => means that to do: is not done yet
            1 => means that done
        */
        when (status) {
            0 -> return 'D'
            else -> return 'A'
        }
    }
}