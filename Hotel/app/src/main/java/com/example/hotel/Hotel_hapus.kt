package com.example.hotel

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Hotel_hapus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_hapus)

    //tangkap dulu id_mahasiswa_terpilih. karena dua yang mau kita hapus
    val pilih_hotel:String = intent.getStringExtra("pilih_hotel").toString()
    //konek ke db
    val dbhotel: SQLiteDatabase = openOrCreateDatabase("hotel", MODE_PRIVATE, null)

    //query hapus data
    val query = dbhotel.rawQuery("DELETE FROM hotel WHERE id_hotel='$pilih_hotel'", null)
    query.moveToNext()

    val pindah: Intent = Intent(this, Hotel::class.java)
    startActivity(pindah)
    }
}