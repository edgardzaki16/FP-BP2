package com.example.hotel

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Info_Hotel_Item(
    val ini: Context,
    val id: MutableList<String>,
    val nama: MutableList<String>,
    val alamat: MutableList<String>,
    val foto: MutableList<Bitmap>
) : RecyclerView.Adapter<Info_Hotel_Item.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_nama: TextView = itemView.findViewById(R.id.txt_nama)
        val txt_alamat: TextView = itemView.findViewById(R.id.txt_alamat)
        val iv_foto: ImageView = itemView.findViewById(R.id.iv_foto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.info_hotel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_nama.text = nama[position]
        holder.txt_alamat.text = alamat[position]
        holder.iv_foto.setImageBitmap(foto[position])
    }

    override fun getItemCount(): Int {
        return nama.size
    }
}
