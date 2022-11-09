package com.shocker.mongo_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.UserAlreadyExistsException
import kotlinx.coroutines.runBlocking
import javax.security.auth.callback.Callback

class RegisterUser : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var register:Button
    lateinit var app:App
    var tempmail:String=""
    val appid:String="mongodbapp-cpytv"
    var message:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        email=findViewById(R.id.et1)
        pass=findViewById(R.id.et2)
        register=findViewById(R.id.button3)
        app= App.Companion.create(appid)
        register.setOnClickListener {
            registeruser()
        }

        val gotologin:Button=findViewById(R.id.button4)
        gotologin.setOnClickListener {
            val intent: Intent =Intent(this,LoginUser::class.java)
            startActivity(intent)
        }

    }


    fun registeruser(){
        val emaildata=email.text.toString()
        tempmail=emaildata
        val password=pass.text.toString()
        runBlocking {

            try{
                app.emailPasswordAuth.registerUser(emaildata,password)
                val user=app.currentUser
                check(user!!)
            }
            catch (e:UserAlreadyExistsException){
               message="sorry user already exist"
                getmessage()
            }

        }

    }

     fun check(user: User){
        if(user.equals(null)){
            Toast.makeText(this,"Registration failed",Toast.LENGTH_LONG).show()
        }
        else{

            Toast.makeText(this,"Registration successfull",Toast.LENGTH_LONG).show()

        }
    }

    fun getmessage(){
        Toast.makeText(this,"Sorry $message",Toast.LENGTH_LONG).show()
    }
}