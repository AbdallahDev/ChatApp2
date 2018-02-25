package com.yazan.it.chatapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MessageAdapter(var context:Context,var list:ArrayList<Messages>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1)
        {
            var v=LayoutInflater.from(context).inflate(R.layout.sender_row,
                    parent,false)
            return SM(v)
        }
        else
        {
            var v=LayoutInflater.from(context).inflate(R.layout.receiver_row,
                    parent,false)
            return RM(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if(holder?.itemViewType==1)
            (holder as SM).bind(list.get(position).msg)
        else
            (holder as RM).bind(list.get(position).msg)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {

        if(list.get(position).name.equals("me"))
            return 1
        else
            return 2
    }

    class RM(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        var t=itemView.findViewById<TextView>(R.id.tv)
        fun bind(m:String)
        {
            t.text=m
        }
    }

    class SM(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        var t=itemView.findViewById<TextView>(R.id.tv)
        fun bind(m:String)
        {
            t.text=m
        }
    }

}
