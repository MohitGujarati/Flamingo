package com.example.flamingo.data.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.flamingo.data.model.NewsSavedModel

class SavednewsHelper(var context: Context) :
    SQLiteOpenHelper(context, TABLE_NAME, null, DATABASE_VERSION) {

    companion object {
        var TABLE_NAME = "savednewstable"
        var DATABASE_VERSION = 1
        var newsid = "key_id"
        var newstitle = "news_Title"
        var newsdescription = "news_description"
        var newlink = "news_linkn"
        var newsImage = "news_Image"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            (
                    "CREATE TABLE " + TABLE_NAME.toString() + " "
                            + " ( " + newsid.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + newstitle.toString() + " TEXT,"
                            + newsdescription.toString() + " TEXT,"
                            + newlink.toString() + " TEXT,"
                            + newsImage.toString() + " TEXT)"
                    )
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insert(modelclass: NewsSavedModel): Long {

        var note_db = this.writableDatabase

        var note_cv = ContentValues()


        note_cv.put(newstitle, modelclass.newstitle)
        note_cv.put(newsdescription, modelclass.newsdescription)
        note_cv.put(newsImage, modelclass.newsImage)
        note_cv.put(newlink, modelclass.newlink)


        var insert = note_db.insert(TABLE_NAME, null, note_cv)
        return insert
    }

    @SuppressLint("Range")
    fun retrieve(): ArrayList<NewsSavedModel> {

        var userdatalist: MutableList<NewsSavedModel> = ArrayList<NewsSavedModel>()


        var query = "select * from $TABLE_NAME order by $newsid desc"


        var cursor: Cursor?
        var notedatabase = this.writableDatabase


        try {
            cursor = notedatabase.rawQuery(query, null)
        }
        catch (Exp: SQLiteException) {
            notedatabase.execSQL(query)
            return ArrayList()
        }

        var dbid: Int
        var titleid: String
        var descriptionid: String
        var linkid: String
        var imageid: String


        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    dbid = cursor.getInt(cursor.getColumnIndex(newsid))
                    titleid = cursor.getString(cursor.getColumnIndex(newstitle))
                    descriptionid = cursor.getString(cursor.getColumnIndex(newsdescription))
                    linkid = cursor.getString(cursor.getColumnIndex(newlink))
                    imageid = cursor.getString(cursor.getColumnIndex(newsImage))


                    var userdatas =
                        NewsSavedModel(dbid, titleid, descriptionid,linkid,imageid)
                    userdatalist.add(userdatas)


                } while (cursor.moveToNext())
            }
        }

        return userdatalist as ArrayList<NewsSavedModel> /* = java.util.ArrayList<mohit.dev.interviewprep1.Model.Model_History> */
    }

}