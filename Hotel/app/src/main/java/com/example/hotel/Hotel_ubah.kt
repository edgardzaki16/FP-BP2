package com.example.hotel

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Hotel_ubah : AppCompatActivity() {
    // Deklarasikan variabel di sini, tetapi inisialisasikan di dalam metode onCreate
    lateinit var pilih_hotel: String
    lateinit var dbhotel: SQLiteDatabase
    lateinit var jikok: Cursor
    lateinit var a_nama: String
    lateinit var a_alamat: String
    lateinit var edt_nama: EditText
    lateinit var edt_alamat: EditText
    lateinit var btn_simpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_ubah)

        // Inisialisasi variabel di sini
        pilih_hotel = intent.getStringExtra("pilih_hotel").toString()
        dbhotel = openOrCreateDatabase("hotel", MODE_PRIVATE, null)
        jikok = dbhotel.rawQuery("SELECT * FROM hotel WHERE id_hotel = '$pilih_hotel'", null)
        jikok.moveToNext()

        a_nama = jikok.getString(1)
        a_alamat = jikok.getString(2)

        edt_nama = findViewById(R.id.edt_nama)
        edt_alamat = findViewById(R.id.edt_alamat)
        btn_simpan = findViewById(R.id.btn_simpan)

        edt_nama.setText(a_nama)
        edt_alamat.setText(a_alamat)

        // btn simpan ditekan
        btn_simpan.setOnClickListener {
            // Mendapatkan inputan yang baru dari kotak edt_nim, edt_nama
            val nama_baru: String = edt_nama.text.toString()
            val alamat_baru: String = edt_alamat.text.toString()

            val ngubah = dbhotel.rawQuery("UPDATE hotel SET nama_hotel='$nama_baru', alamat_hotel='$alamat_baru' WHERE id_hotel='$pilih_hotel'", null)
            ngubah.moveToNext()

            val pindah: Intent = Intent(this, Hotel_item::class.java)
            startActivity(pindah)
        }
    }
}