package com.vimalnath.tab.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vimalnath.tab.ChatBotActivity
import com.vimalnath.tab.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        view.talkToBotBtn.setOnClickListener {
            Log.d("talkToBotBtn","Selected")

            val intent = Intent(activity, ChatBotActivity::class.java )
            activity?.startActivity(intent)
        }
        return view

    }
}


