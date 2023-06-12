package com.array.androidreviewproject.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.array.androidreviewproject.R
import com.array.androidreviewproject.bean.User

/**
 * RecyclerView的Adapter的构建步骤：
 * 1、新建类，传入相关的list、Activity、Fragment等
 * 2、新建内部类，继承自RecyclerView.ViewHolder
 * 3、外部类继承自RecyclerView.Adapter<MyAdapter.MyViewHolder>
 */
class MyAdapter(private val userList: List<User>) :  RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName)
        val userAge: TextView = view.findViewById(R.id.userAge)
        val userImg: ImageView = view.findViewById(R.id.userImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val holder = MyViewHolder(view)
        holder.itemView.setOnClickListener {

        }
        return holder
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.userName
        holder.userAge.text = user.userAge.toString()
        holder.userImg.setImageResource(R.mipmap.ic_launcher)
    }
}