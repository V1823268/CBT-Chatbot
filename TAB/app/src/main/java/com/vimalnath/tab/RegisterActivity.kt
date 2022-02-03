package com.vimalnath.tab

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.register_ui.*
import kotlinx.coroutines.DelicateCoroutinesApi


@DelicateCoroutinesApi
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_ui)

        registerbtn.setOnClickListener {
            when {
                TextUtils.isEmpty(reg_email.text.toString().trim() { it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(reg_password.text.toString().trim() { it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter your password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else -> {

                    val email: String = reg_email.text.toString().trim(){ it <= ' '}
                    val password: String = reg_password.text.toString().trim(){ it <= ' '}



                //Create instance of register user w/ email & password
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                        //if registration successful
                        if (task.isSuccessful) {

                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration Successful!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            firebaseUser.sendEmailVerification()
                            startActivity(intent)
                            finish()
                        } else {
                            //When registration Unsuccessful
                            Toast.makeText(
                                this@RegisterActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()


                        }
                    }
            }
            }
        }

        //sign in button will lead to login page
        registereduserbtn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }


}