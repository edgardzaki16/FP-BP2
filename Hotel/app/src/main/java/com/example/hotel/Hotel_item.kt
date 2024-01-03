package com.example.hotel

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class Hotel_item(
    val ini: Context,
    val id: MutableList<String>,
    val nama: MutableList<String>,
    val alamat: MutableList<String>,
    val foto: MutableList<Bitmap>
) : RecyclerView.Adapter<Hotel_item.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_nama: TextView = itemView.findViewById(R.id.txt_nama)
        val txt_alamat: TextView = itemView.findViewById(R.id.txt_alamat)
        val iv_foto: ImageView = itemView.findViewById(R.id.iv_foto)
        val btn_ubah: Button = itemView.findViewById(R.id.btn_ubah)
        val btn_hapus: Button = itemView.findViewById(R.id.btn_hapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nama.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_nama.text = nama[position]
        holder.txt_alamat.text = alamat[position]
        holder.iv_foto.setImageBitmap(foto[position])

        // btn_hapus ditekan
        holder.btn_hapus.setOnClickListener {
            // dapatkan id_mahasiswa sesuai urutan dari tombol yang ditekan
            val pilih_hotel: String = id[position]

            // larikan ke activity Mahasiswa_hapus
            val pindah: Intent = Intent(ini, Hotel_hapus::class.java)
            pindah.putExtra("pilih_hotel", pilih_hotel)
            ini.startActivity(pindah)
        }

        // btn ubah ditekan
        holder.btn_ubah.setOnClickListener {
            // dapatkan id_mahasiswa sesuai urutan dari tombol yang ditekan
            val pilih_hotel: String = id[position]

            // larikan ke activity Mahasiswa_ubah
            val pindah: Intent = Intent(ini, Hotel_ubah::class.java)
            pindah.putExtra("pilih_hotel", pilih_hotel)
            ini.startActivity(pindah)
        }
    }
}
