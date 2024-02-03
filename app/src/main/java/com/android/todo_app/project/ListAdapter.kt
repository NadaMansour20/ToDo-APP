package com.android.todo_app.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.todo_app.R
import com.android.todo_app.database.Todo

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    var datalist: MutableList<Todo>?

    constructor(datalist: MutableList<Todo>?) : super() {
        this.datalist = datalist
    }


    class ListViewHolder : RecyclerView.ViewHolder {
        var taskname: TextView? = null
        var description: TextView? = null
        var done: ImageView? = null
        var carditem: CardView? = null
        var iconphoto_delete: ImageView? = null

        constructor(itemView: View) : super(itemView) {

            taskname = itemView.findViewById(R.id.taskname)
            description = itemView.findViewById(R.id.description)
            done = itemView.findViewById(R.id.done)
            iconphoto_delete = itemView.findViewById(R.id.delete)
            carditem = itemView.findViewById(R.id.cardview)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.todoitem, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist?.size ?: 0   //because perhaps list=null
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataitem = datalist!!.get(position)
        holder.taskname!!.setText(dataitem.name)
        holder.description!!.setText(dataitem.detailes)

        if (itemclickedDeleteAndUpdate != null) {
            holder.carditem?.setOnClickListener {
                itemclickedDeleteAndUpdate?.UpdateItem(position, dataitem)
            }
            holder.iconphoto_delete?.setOnClickListener {
                itemclickedDeleteAndUpdate?.DeletedItem(position, dataitem)
            }
            holder.done?.setOnClickListener {
                itemclickedDeleteAndUpdate?.Done(holder.done!!, holder.taskname!!)
            }
        }
    }

    fun changeData(newdatalist: MutableList<Todo>) {
        this.datalist = newdatalist
        notifyDataSetChanged()    //define adapter that the data has been changed
    }

    var itemclickedDeleteAndUpdate: todoItemclicked? = null

    interface todoItemclicked {
        fun DeletedItem(position: Int, todo: Todo)
        fun UpdateItem(position: Int, todo: Todo)
        fun Done(done: ImageView, taskname: TextView)
    }
}