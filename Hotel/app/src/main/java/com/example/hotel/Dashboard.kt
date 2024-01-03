package com.example.hotel

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val user_login: TextView = findViewById(R.id.user_login)

        val masuk:SharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val user_email:String = masuk.getString("email_user", null).toString()
        user_login.text = user_email

        //logout
        val btn_logout:Button = findViewById(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val keluarin =  masuk.edit()
            keluarin.clear()
            keluarin.commit()

            //pindah ke login
            val keluar:Intent = Intent(this, Login::class.java)
            startActivity(keluar)
            finish()
        }

        val dbhotel:SQLiteDatabase = openOrCreateDatabase("hotel", MODE_PRIVATE, null)

        val user = dbhotel.rawQuery("SELECT * FROM user", null)

        val btn_hotel:Button = findViewById(R.id.btn_hotel)
        btn_hotel.setOnClickListener {
            val flicker:Intent = Intent(this, Hotel::class.java)
            startActivity(flicker)
        }

        val btn_info_hotel: Button = findViewById(R.id.btn_info_hotel)
        btn_info_hotel.setOnClickListener {
            val intent = Intent(this, Info_Hotel::class.java)
            startActivity(intent)
        }

    }
}