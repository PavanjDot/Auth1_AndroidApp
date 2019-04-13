package com.plantquiz.logindemo

import android.content.Intent
import android.icu.text.IDNA
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_secondactivity.*
import javax.security.auth.login.LoginException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var name: EditText = findViewById(R.id.etName)
        var password: EditText = findViewById(R.id.etPassword)
        var rgistertv: TextView = findViewById(R.id.tvRegister)
        var info: TextView = findViewById(R.id.tvInfo)
        var Login: Button = findViewById(R.id.btnLogin)
        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


        Login.setOnClickListener {

            validate(name.getText().toString(), password.getText().toString())
        }

        rgistertv.setOnClickListener(View.OnClickListener {

            //            var gotoRegister = Intent(this, RegistrationActivity::class.java)
//            startActivity(gotoRegister)
            val Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(Intent)
        })


        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            finish()
            val Intent = Intent(this, secondactivity::class.java)
        }


    }

    fun validate(userName: String, userPassword: String){

        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(userName, userPassword)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    //Registration OK
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    val firebaseUser = firebaseAuth.currentUser!!
                    val Intent = Intent(this, secondactivity::class.java)

                } else {
                    //Registration error
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
    }






