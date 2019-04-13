package com.plantquiz.logindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    var userName: EditText? = null
    var userPassword: EditText? = null
    var userEmail: EditText? = null
    var regButton: Button? = null
    var userLogin: TextView? = null
    var firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setupUIViews()


        regButton?.setOnClickListener(View.OnClickListener {

            if (validate()) {
                var user_email = userEmail?.getText().toString().trim()
                var user_password = userPassword?.getText().toString().trim()

                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password)
                    .addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            //Registration OK
                            val firebaseUser = firebaseAuth.currentUser!!
                        } else {
                            //Registration error
                        }
                    }
            }
        })

        userLogin?.setOnClickListener(View.OnClickListener {


           var changeActivity = Intent(this, MainActivity::class.java)

            startActivity(changeActivity)
        })





    }

    fun setupUIViews() {

        var userName: EditText = findViewById(R.id.etUserName)
        var userPassword: EditText = findViewById(R.id.etPassword)
        var userEmail: EditText = findViewById(R.id.etUserEmail)
        var regButton: Button = findViewById(R.id.btnRegister)
        var userLogin: TextView = findViewById(R.id.tvUserLogin)

    }


    fun validate(): Boolean{

        var result = false

        var name = userName?.getText().toString()
        var email = userEmail?.getText().toString()
        var password = userPassword?.getText().toString()

        if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Please Enter All the Details", Toast.LENGTH_SHORT).show()
        }else{
            result = true
        }

return result
    }
}
