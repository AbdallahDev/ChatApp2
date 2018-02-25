package com.yazan.it.chatapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChatDB(context: Context) : SQLiteOpenHelper(context,
        "chat.db",null,1)
{
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table chat(name text,msg text)")
        p0?.execSQL("insert into chat values('me','Hello')")
        p0?.execSQL("insert into chat values('Ali','Hi how r u ?')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}
