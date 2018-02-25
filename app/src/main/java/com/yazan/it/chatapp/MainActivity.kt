package com.yazan.it.chatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbr=FirebaseDatabase.getInstance().getReference()

        var list=ArrayList<Messages>()

        var obj=ChatDB(this)
        var db=obj.writableDatabase
        var cur=db.rawQuery("select * from chat",
                null)
        cur.moveToFirst()
        while(cur.isAfterLast==false)
        {
            list.add(Messages(cur.getString(0),
                    cur.getString(1)))
            cur.moveToNext()
        }


        var adp=MessageAdapter(this,list)
        rv.adapter=adp
        rv.layoutManager=LinearLayoutManager(this)
        rv.scrollToPosition(list.size-1)

        dbr.child("dhoyazan").addValueEventListener(object:
                ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                list.add(Messages("abdulla",p0?.value.toString()))
                adp.notifyDataSetChanged()
                db.execSQL("insert into chat values(?,?)",
                        arrayOf("abdulla",p0?.value.toString()))
                rv.scrollToPosition(list.size-1)
            }

        })

        btn.setOnClickListener {

            list.add(Messages("me",chat_et.text.toString()))
            adp.notifyDataSetChanged()

            dbr.child("abdulla").setValue(chat_et.text.toString())

            db.execSQL("insert into chat values(?,?)",
                    arrayOf("me",chat_et.text.toString()))

            chat_et.setText("")

        }

    }
}
