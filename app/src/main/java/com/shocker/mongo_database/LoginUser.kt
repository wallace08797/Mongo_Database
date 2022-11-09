package com.shocker.mongo_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.InvalidCredentialsException
import kotlinx.coroutines.runBlocking

class LoginUser : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var pass:EditText
    val appid:String="mongodbapp-cpytv"
    var message:String=""
    var successmessage:String="login successfull welcome user"
    var tempmail:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        val login: Button = findViewById(R.id.button5)
        login.setOnClickListener {
            loginuser()
        }

    }


    fun loginuser(){
        email=findViewById(R.id.et3)
        pass=findViewById(R.id.et4)
        val maildata=email.text.toString()
        tempmail=maildata
        val passdata=pass.text.toString()
        val app = App.create(appid)
        val credentials=Credentials.emailPassword(maildata,passdata)
        runBlocking {
            try{
                app.login(credentials)
                getsuccessmessage(successmessage)
            }
            catch (e:InvalidCredentialsException){
                message="sorry please check your email and password"
                getmesaage(message)
            }

        }
    }

    fun getmesaage(message:String):String{
        return Toast.makeText(this, message,Toast.LENGTH_LONG).show().toString()
    }

    fun getsuccessmessage(message: String):String{
        return Toast.makeText(this,message,Toast.LENGTH_LONG).show().toString()
    }
}