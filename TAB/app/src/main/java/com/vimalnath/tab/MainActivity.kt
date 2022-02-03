package com.vimalnath.tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vimalnath.tab.fragments.*
import kotlinx.android.synthetic.main.home_ui.*
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val journalFragment = JournalFragment()
    private val profileFragment = ProfileFragment()
    private val settingsFragment = SettingsFragment()
    private val sosFragment = SOSFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_ui)



        replaceFragment(homeFragment)

        Top_Navbar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.sos -> replaceFragment(sosFragment)
                R.id.settings -> replaceFragment(settingsFragment)
            }
            true
        }

        Bottom_Navbar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.journal -> replaceFragment(journalFragment)
                R.id.profile -> replaceFragment(profileFragment)
            }
            true
        }

        //val userId = intent.getStringExtra("user_id")

        //user_id.text = "USER ID :: $userId"

        //chatbotBtn.setOnClickListener{

         //   startActivity(Intent(this@HomeActivity, ChatBotActivity::class.java))
       // }

        //signoutbtn.setOnClickListener {
            // logout firebase
          //  FirebaseAuth.getInstance().signOut()

          //  startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
          //  finish()
       // }
    }

    private fun replaceFragment(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}

