package com.shocker.mongo_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.realm.gradle.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    val appid:String="mongodbapp-cpytv"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login: Button = findViewById(R.id.button)
        val logout: Button = findViewById(R.id.button2)
        login.setOnClickListener {
            annonyumuslogin()
        }
        logout.setOnClickListener {
            logoutuser()
        }
    }


    fun annonyumuslogin(){
        val realm: Unit =io.realm.Realm.init(this)
        val app:App= App(AppConfiguration.Builder(appid).build())
        //this line of code helps us to establish an connection with the mongoDB Realm and here appid refers to the id of the application that we registered in MongoDB.
        val credentials:Credentials= Credentials.anonymous()
        //this line of code creates annonymus credentials for the user to use them and login annonymusly.
        app.loginAsync(credentials, App.Callback {
            if(it.isSuccess){
                Toast.makeText(this,"user logged in annonyumusly",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"logging in failed",Toast.LENGTH_LONG).show()
            }
        })

        //meaning of this code is that here loginAsync means that the loginfunction is executed in the asynchronus manner meaning the UI would not be blocked till the user is not
        //logged in meaning in general async tasks are those in which we can perfrom heavy network calls in background letting our main thread light and thus not blocking our User Interface.
        //here we have an callbackfunction -> this are the type of functions which are passed as arguments while calling an outer function and invoked inside the outerfunction to complete or to perform
        //certain type of action so here we would receive an toast message whenever our user successfully login or an error message if the login fails.

    }


    fun logoutuser(){
        val app:App= App(AppConfiguration.Builder(appid).build())
        app.currentUser()?.logOutAsync(App.Callback {

            if(it.isSuccess){
                Toast.makeText(this,"user logged out successfully",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"failed logging out",Toast.LENGTH_LONG).show()
            }
        })
    }



}