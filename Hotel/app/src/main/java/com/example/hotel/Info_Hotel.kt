package com.example.hotel

import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayInputStream
import java.lang.Exception

class Info_Hotel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_hotel)

        val rv_info_hotel: RecyclerView = findViewById(R.id.rv_info_hotel)

        val id:MutableList<String> = mutableListOf()
        val nama:MutableList<String> = mutableListOf()
        val alamat: MutableList<String> = mutableListOf()
        val foto:MutableList<Bitmap> = mutableListOf()

        val dbhotel: SQLiteDatabase = openOrCreateDatabase("hotel", MODE_PRIVATE, null)
        val hotel_transylvania = dbhotel.rawQuery("SELECT * FROM hotel", null)

        while (hotel_transylvania.moveToNext()){
            try {
                val motor = ByteArrayInputStream(hotel_transylvania.getBlob(3))
                val fotobitmap:Bitmap = BitmapFactory.decodeStream(motor)
                foto.add(fotobitmap)
            } catch (a: Exception){
                val fotobitmap:Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.icon_kamar)
                foto.add(fotobitmap)
            }
            id.add(hotel_transylvania.getString(0))
            nama.add(hotel_transylvania.getString(1))
            alamat.add(hotel_transylvania.getString(2))
        }
        val miui = Info_Hotel_Item(this, id, nama, alamat, foto)
        rv_info_hotel.adapter = miui
        rv_info_hotel.layoutManager = GridLayoutManager(this,2)
    }
}