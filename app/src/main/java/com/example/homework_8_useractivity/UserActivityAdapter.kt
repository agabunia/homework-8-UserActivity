package com.example.homework_8_useractivity

import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_8_useractivity.databinding.UserInfoLayoutBinding

class UserActivityAdapter(var users: MutableList<Users>, clickListener: ClickListener) :
        RecyclerView.Adapter<UserActivityAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UserInfoLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private var clickListener: ClickListener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserInfoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            tvFirstNameOutput.text = users[position].firstName
            tvLastNameOutput.text = users[position].lastName
            tvEmailOutput.text = users[position].email
        }

        holder.binding.btnDelete.setOnClickListener {
            users.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, users.size)
        }

        holder.binding.btnEdit.setOnClickListener {
            clickListener.clickedUser(position)
        }
    }

    interface ClickListener {
        fun clickedUser(position: Int)
    }
}