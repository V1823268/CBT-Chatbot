package com.vimalnath.tab

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.forgot_password_ui.*
import kotlinx.android.synthetic.main.register_ui.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_ui)

        send_emailBtn.setOnClickListener {

            when {
                TextUtils.isEmpty(forgot_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {

                    val email: String = forgot_email.text.toString().trim { it <= ' '}
                    val actionCodeSettings = actionCodeSettings {

                        url = "https://tab-gauw.firebaseapp.com/?email="

                        setIOSBundleId("com.vimalnath.ios")

                        setAndroidPackageName("com.vimalnath.android",true,"12")

                        handleCodeInApp = true
                        dynamicLinkDomain = "vimalnath.page.link"

                    }

                FirebaseAuth.getInstance().sendPasswordResetEmail(email, actionCodeSettings).addOnCompleteListener { task ->

                    //if registration successful
                    if (task.isSuccessful) {

                        Log.d(TAG, "Email Sent.")

                        val intent =
                            Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()

                    } else {
                        //When registration Unsuccessful
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()


                    }
                }

            }

        }

    }
}



}
