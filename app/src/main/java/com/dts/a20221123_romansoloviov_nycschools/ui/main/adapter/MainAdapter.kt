package com.dts.a20221123_romansoloviov_nycschools.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dts.a20221123_romansoloviov_nycschools.R
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolName
import com.dts.a20221123_romansoloviov_nycschools.databinding.CellSchoolBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: CellSchoolBinding = CellSchoolBinding.bind(view)

        fun bind(schoolName: SchoolName, onClick: ((SchoolName) -> Unit)? = null) {
            binding.tvName.text = schoolName.schoolName
            binding.tvLocation.text = schoolName.location
            binding.btnSchool.setOnClickListener {
                onClick?.invoke(schoolName)
            }
        }
    }

    var items = listOf<SchoolName>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_school, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position], onClickListener)
    }

    override fun getItemCount(): Int = items.size

    var onClickListener: ((SchoolName) -> Unit)? = null
}
