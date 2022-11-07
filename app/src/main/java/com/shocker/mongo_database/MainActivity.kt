package com.shocker.mongo_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.realm.kotlin.Realm
import io.realm.kotlin.internal.interop.Callback
import io.realm.kotlin.internal.interop.SWIGTYPE_p_realm_mongodb_collection
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    val appid:String="mongodbapp-cpytv"
    lateinit var app:App
    lateinit var user:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login:Button=findViewById(R.id.button)
        val logout:Button=findViewById(R.id.button2)
        login.setOnClickListener {
          annonymuslogin()

        }
        logout.setOnClickListener {
            logout()
        }



    }


      fun annonymuslogin(){
       app= App.Companion.create(appid)
        //this would help us to connect with realm database that we created.
        val credentials:Credentials= Credentials.anonymous(reuseExisting = true)
            //here reuseExisting means that it would return the same user everytime the login button is clicked until and unless the user
          //logsout. once it logs out a new user would be created on the server.

          runBlocking {
              app.login(credentials)
              val user=app.currentUser
              check(user!!)
          }

     }

    fun logout(){
       app= App.Companion.create(appid)
       runBlocking {
           app.currentUser?.logOut()
           show()
       }
    }

    fun check(user: User){
        if(user.equals(null)){
            Toast.makeText(this,"user login failed",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"user login successfull",Toast.LENGTH_LONG).show()
        }
    }


    fun show(){
        Toast.makeText(this,"user log out successfull",Toast.LENGTH_LONG).show()
    }



}