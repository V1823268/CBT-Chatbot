package com.vimalnath.tab

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_ui.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class HomeActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_ui)

        val userId = intent.getStringExtra("user_id")

        user_id.text = "USER ID :: $userId"

        chatbotBtn.setOnClickListener{

            startActivity(Intent(this@HomeActivity, ChatBotActivity::class.java))
        }

        signoutbtn.setOnClickListener {
            // logout firebase
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        }
    }
}

