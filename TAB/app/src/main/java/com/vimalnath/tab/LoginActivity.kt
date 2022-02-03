package com.vimalnath.tab

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_ui.*
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_ui)

        signinbtn.setOnClickListener {
            when {
                TextUtils.isEmpty(log_email.text.toString().trim() { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(log_password.text.toString().trim() { it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {

                //Get email and password
                val email: String = log_email.text.toString().trim(){ it <= ' '}
                val password: String = log_password.text.toString().trim(){ it <= ' '}

                //Create instance of signin user w/ email & password
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        //if registration successful
                        if (task.isSuccessful) {

                            Toast.makeText(
                                this@LoginActivity,
                                "You are Signed In!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()
                        } else {
                            //When registration Unsuccessful
                            Toast.makeText(
                                this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()


                        }
                    }
            }
            }
        }



        //sign up button will lead to register page
        register.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }

        //forgot password button will lead to forgot password page
        forgot_pass.setOnClickListener{
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }




}