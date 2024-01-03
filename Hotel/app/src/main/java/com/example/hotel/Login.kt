package com.example.hotel

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val id_email: EditText = findViewById(R.id.id_email)
        val id_password: EditText = findViewById(R.id.id_password)
        val btn_login: Button = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {

            val isi_email: String = id_email.text.toString()
            val isi_password: String = id_password.text.toString()

            val dbhotel: SQLiteDatabase = openOrCreateDatabase("hotel", MODE_PRIVATE, null)

            val query = dbhotel.rawQuery("SELECT * FROM user WHERE email_user='$isi_email' AND password_user='$isi_password'", null)
            val cek = query.moveToNext()

            if (cek) {
                val id = query.getString(0)
                val email = query.getString(1)
                val password = query.getString(2)
                val nama = query.getString(3)

                val session: SharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val masukin = session.edit()
                masukin.putString("id_user", id)
                masukin.putString("email_user", email)
                masukin.putString("password_user", password)
                masukin.putString("nama_user", nama)
                masukin.commit()

                val flicker: Intent = Intent(this, Dashboard::class.java)
                startActivity(flicker)
            } else {
                Toast.makeText(this, "Email atau Password Kamu Salah!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}