package com.vimalnath.tab

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import com.google.firebase.auth.FirebaseAuth
import com.vimalnath.tab.adapter.AgentAdapter
import com.vimalnath.tab.model.Message
import kotlinx.android.synthetic.main.chatbot_ui.*
import kotlinx.android.synthetic.main.home_ui.*
import kotlinx.android.synthetic.main.register_ui.*
import kotlinx.coroutines.*
import java.util.*

@DelicateCoroutinesApi
class ChatBotActivity : AppCompatActivity() {

    private var msgList: ArrayList<Message> = ArrayList()
    private var sessionClient: SessionsClient? = null
    private var sessionName: SessionName? = null
    private val uuid = UUID.randomUUID().toString()
    private val TAG = "ChatBotActivity"
    private lateinit var agentAdapter: AgentAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatbot_ui)

        //setting adapter to recyclerview
        agentAdapter = AgentAdapter(this, msgList)
        chatView.adapter = agentAdapter

        //onclick listener to update the list and call dialogflow
        sendBtn.setOnClickListener {
            val message: String = enterMsg.text.toString()
            if (message.isNotEmpty()) {
                addMsg(message, false)
                msgToTAB(message)
            } else {
                Toast.makeText(this@ChatBotActivity, "Please Enter Some Text! :(", Toast.LENGTH_SHORT).show()
            }
        }

        // Set Up Test Anxiety Bot
        setUpTAB()
    }

    // Add Message to List function
    @SuppressLint("NotifyDataSetChanged")
    private fun addMsg(message: String, isReceived: Boolean) {
        msgList.add(Message(message, isReceived))
        enterMsg.setText("")
        agentAdapter.notifyDataSetChanged()
        chatView.layoutManager?.scrollToPosition(msgList.size - 1)
    }

    //DialogFlow Integration
    private fun setUpTAB() {
        try {
            val stream = this.resources.openRawResource(R.raw.sa_key)
            val credentials: GoogleCredentials = GoogleCredentials.fromStream(stream)
                .createScoped("https://www.googleapis.com/auth/cloud-platform")
            val projectId: String = (credentials as ServiceAccountCredentials).projectId
            val settingsBuilder: SessionsSettings.Builder = SessionsSettings.newBuilder()
            val sessionsSettings: SessionsSettings = settingsBuilder.setCredentialsProvider(
                FixedCredentialsProvider.create(credentials)
            ).build()
            sessionClient = SessionsClient.create(sessionsSettings)
            sessionName = SessionName.of(projectId, uuid)
            Log.d(TAG, "projectId : $projectId")
        } catch (e: Exception) {
            Log.d(TAG, "setUpBot: " + e.message)
        }
    }

    //Send Message Function
    private suspend fun sendMsg(
        queryInput: QueryInput
    ) {
        withContext(Dispatchers.Default) {
            try {
                val detectIntentRequest = DetectIntentRequest.newBuilder()
                    .setSession(sessionName.toString())
                    .setQueryInput(queryInput)
                    .build()
                val result = sessionClient?.detectIntent(detectIntentRequest)
                if (result != null) {
                    runOnUiThread {
                        updtTAB(result)
                    }
                }
            } catch (e: java.lang.Exception) {
                Log.d(TAG, "doInBackground: " + e.message)
                e.printStackTrace()
            }
        }
    }

    //Send Message to TAB
    private fun msgToTAB(message: String) {
        val input = QueryInput.newBuilder()
            .setText(TextInput.newBuilder().setText(message).setLanguageCode("en-US")).build()
        GlobalScope.launch {
            sendMsg(input)
        }
    }

    //Update TAB UI
    private fun updtTAB(response: DetectIntentResponse) {
        val botReply: String = response.queryResult.fulfillmentText
        if (botReply.isNotEmpty()) {
            addMsg(botReply, true)
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
        }
    }


}






