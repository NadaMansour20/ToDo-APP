package com.android.todo_app.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.todo_app.R
import com.android.todo_app.database.Todo

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>{
    var datalist:MutableList<Todo>?=null

    constructor(datalist:MutableList<Todo>?) : super() {
        this.datalist = datalist
    }


    class ListViewHolder: RecyclerView.ViewHolder{
        var taskname:TextView?=null
        var description:TextView?=null
        var done:ImageView?=null
        constructor(itemView: View) : super(itemView){

            taskname=itemView.findViewById(R.id.taskname)
            description=itemView.findViewById(R.id.description)
            done=itemView.findViewById(R.id.done)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.todoitem,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist?.size?:0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var data=datalist!!.get(position)
        holder.taskname!!.setText(data.name)
        holder.description!!.setText(data.detailes)
    }

    fun changeData(newdatalist:MutableList<Todo>){
        this.datalist=newdatalist
        notifyDataSetChanged()
    }
}