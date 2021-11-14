package com.example.pengingatjadwal.Jadwal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pengingatjadwal.Adapter.RecJadwalAdapter
import com.example.pengingatjadwal.Adapter.RecRiwayatAdapter
import com.example.pengingatjadwal.Adapter.RecSemuaJadwalItem
import com.example.pengingatjadwal.Database.DbHelper
import com.example.pengingatjadwal.Model.JadwalModel
import com.example.pengingatjadwal.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragmentRiwayat: Fragment(), RecSemuaJadwalItem {

    //Variabel View
    lateinit var rootView: View
    lateinit var recRiwayat: RecyclerView

    lateinit var recAdapter: RecRiwayatAdapter
    lateinit var listHistorySchedule: MutableList<JadwalModel>
    lateinit var dbHelper: DbHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_riwayat, null, false)

        initView()

        getHistorySchedule()
        setRecData()

        return rootView
    }

    //Fungsi Inisialisasi View
    private fun initView() {
        dbHelper = DbHelper(requireContext())

        recRiwayat= rootView.findViewById(R.id.rec_riwayat)
    }

    //Fungsi Mendapatkan Riwayat Jadwal
    fun getHistorySchedule() {
        listHistorySchedule = dbHelper.getAllHistorySchedule()
    }

    //Fungsi Mengeset Data ke Adapter
    fun setRecData() {
        recAdapter = RecRiwayatAdapter(listHistorySchedule, this, 3)
        recRiwayat.layoutManager = LinearLayoutManager(requireContext())
        recRiwayat.adapter = recAdapter
    }

    override fun onDelete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onUpdate(jadwalModel: JadwalModel) {
        val btmSheetCatatanView = LayoutInflater.from(requireContext())
            .inflate(R.layout.view_btm_sheet_catatan, null, false)

        val tvNotes = btmSheetCatatanView.findViewById<TextView>(R.id.tv_catatan_view_btm_sheet_catatan)
        val mbtClose = btmSheetCatatanView.findViewById<TextView>(R.id.mbt_tutup_view_btm_sheet_catatan)

        tvNotes.text = jadwalModel.catatan

        val btmSheetCatatan = BottomSheetDialog(requireContext())
        btmSheetCatatan.setContentView(btmSheetCatatanView)
        btmSheetCatatan.show()

        mbtClose.setOnClickListener {
            btmSheetCatatan.dismiss()
        }
    }
}