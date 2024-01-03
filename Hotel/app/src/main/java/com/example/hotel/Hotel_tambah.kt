package com.example.hotel

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream

class Hotel_tambah : AppCompatActivity() {

    var urlgambar: Uri? = null
    var bitmapgambar: Bitmap? = null
    var iv_upload: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_tambah)

        val edt_nama: EditText = findViewById(R.id.edt_nama)
        val edt_alamat: EditText = findViewById(R.id.edt_alamat)
        val btn_simpan: Button = findViewById(R.id.btn_simpan)

        iv_upload = findViewById(R.id.iv_upload)

        //iv_upload diklik, buka galeri
        iv_upload?.setOnClickListener {
            val bukagaleri: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            pilih_gambar.launch(bukagaleri)
        }

        //btn simpan ditekan
        btn_simpan.setOnClickListener{
            //dapatkan isi dari edt_nim dan edt_nama
            val isi_nama:String = edt_nama.text.toString()
            val isi_alamat:String = edt_alamat.text.toString()

            //dapatkan gambar yg dipilih lalu dijadikan bytearray
            val bos = ByteArrayOutputStream()
            bitmapgambar?.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bytearraygambar = bos.toByteArray()

            //query simpan ke database
            val dbhotel: SQLiteDatabase = openOrCreateDatabase("hotel", MODE_PRIVATE, null)

            val sql = "INSERT INTO hotel (nama_hotel, alamat_hotel ,foto_hotel) VALUES (?,?,?)"
            val statement = dbhotel.compileStatement(sql)
            statement.clearBindings()
            statement.bindString(1, isi_nama)
            statement.bindString(2, isi_alamat)
            statement.bindBlob(3, bytearraygambar)
            statement.executeInsert()



            //pindah lagi dari Mahasiswa_tambah ke Mahasiswa
            val pindah: Intent = Intent(this, Hotel::class.java)
            startActivity(pindah)
        }
    }
    val pilih_gambar = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode== Activity.RESULT_OK) {
            val gambardiperoleh = it.data
            if (gambardiperoleh!=null) {
                //dapatkan urinya gambar
                urlgambar = gambardiperoleh.data

                //konversi ke bitmap
                bitmapgambar = MediaStore.Images.Media.getBitmap(contentResolver, urlgambar)
                iv_upload?.setImageBitmap(bitmapgambar)
            }
        }
    }
}