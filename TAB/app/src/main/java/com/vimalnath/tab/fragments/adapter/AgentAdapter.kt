package com.vimalnath.tab.fragments.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vimalnath.tab.R
import com.vimalnath.tab.fragments.model.Message

// Creating RecyclerView
class AgentAdapter(private var agnt: Activity, private var msgList: List<Message>) : RecyclerView.Adapter<AgentAdapter.ViewHolder>() {

    //Bot and User Messages class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var botMsg: TextView = itemView.findViewById(R.id.botMsg)
        var userMsg: TextView = itemView.findViewById(R.id.userMsg)
    }

    //New ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(agnt).inflate(R.layout.chat_layout, parent, false)
        return ViewHolder(view)
    }

    //Binding ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: String = msgList[position].message
        val msgReceived: Boolean = msgList[position].msgReceived
        if (msgReceived) {
            holder.botMsg.visibility = View.VISIBLE
            holder.userMsg.visibility = View.GONE
            holder.botMsg.text = message
        } else {
            holder.userMsg.visibility = View.VISIBLE
            holder.botMsg.visibility = View.GONE
            holder.userMsg.text = message
        }
    }

    //Returns list of messages
    override fun getItemCount(): Int {
        return msgList.count()
    }

}