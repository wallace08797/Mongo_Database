package com.shocker.mongo_database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class LoginUser : AppCompatActivity() {
    val appid:String="mongodbapp-cpytv"
    lateinit var email:EditText
    lateinit var pass:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)
        val login: Button = findViewById(R.id.button5)
        email=findViewById(R.id.et3)
        pass=findViewById(R.id.et4)
        val resetpassword:Button=findViewById(R.id.button6)
       login.setOnClickListener {
           loginuser()
       }
        resetpassword.setOnClickListener {
            resetpassword()
        }

    }

    fun loginuser(){
        Realm.init(this)
        val app=App(AppConfiguration.Builder(appid).build())

        val emaildata=email.text.toString()
        val passdata=pass.text.toString()
        val credentials=Credentials.emailPassword(emaildata,passdata)
        app.loginAsync(credentials, App.Callback {

            if(it.isSuccess){
                Toast.makeText(this,"Login successfull",Toast.LENGTH_LONG).show()
                val intent:Intent=Intent(this,uploadData::class.java)
                startActivity(intent)
            }
            else{
                val errormessage=it.error.errorMessage.toString()
                Toast.makeText(this,errormessage,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun resetpassword(){
        Realm.init(this)
        val app=App(AppConfiguration.Builder(appid).build())
        val emaildata=email.text.toString()
        app.emailPassword.callResetPasswordFunctionAsync(emaildata,"ahirfenil98980", arrayOf(0), App.Callback {
            if(it.isSuccess){
                Toast.makeText(this,"password reset successfull",Toast.LENGTH_LONG).show()
            }
            else{
                val errormessage=it.error.errorMessage.toString()
                Toast.makeText(this,errormessage,Toast.LENGTH_LONG).show()
            }
        })

    }
}