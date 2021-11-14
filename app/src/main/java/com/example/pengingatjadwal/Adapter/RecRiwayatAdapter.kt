package com.example.pengingatjadwal.Adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pengingatjadwal.Model.JadwalModel
import com.example.pengingatjadwal.R
import com.google.android.material.button.MaterialButton

class RecRiwayatAdapter(val listJadwal: MutableList<JadwalModel>, val recSemuaJadwalItem: RecSemuaJadwalItem, val code: Int): RecyclerView.Adapter<RecRiwayatAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.row_riwayat, null, false),
        recSemuaJadwalItem
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDataToView(listJadwal[position])
    }

    override fun getItemCount(): Int = listJadwal.size

    class ViewHolder(val v : View, val recSemuaJadwalItem: RecSemuaJadwalItem): RecyclerView.ViewHolder(v) {

        val tvMapel = v.findViewById<TextView>(R.id.tv_mapel_riwayat)
        val tvJam = v.findViewById<TextView>(R.id.tv_jam_riwayat)
        val tvKelas = v.findViewById<TextView>(R.id.tv_kelas_riwayat)
        val mbtInfo = v.findViewById<MaterialButton>(R.id.mbt_info_riwayat)

        //Fungsi atur data dari Model
        fun setDataToView(jadwalModel: JadwalModel) {
            tvMapel.text = jadwalModel.mapel
            tvJam.text = jadwalModel.waktu
            tvKelas.text = jadwalModel.kelas

            mbtInfo.setOnClickListener {
                recSemuaJadwalItem.onUpdate(jadwalModel)
            }
        }
    }

}