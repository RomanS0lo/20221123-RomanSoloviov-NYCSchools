package com.dts.a20221123_romansoloviov_nycschools.ui.school

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment
import com.dts.a20221123_romansoloviov_nycschools.data.domain.utils.safeDismiss
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolName
import com.dts.a20221123_romansoloviov_nycschools.databinding.DialogFragmentSchoolBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SchoolDialogFragment(private val schoolName: SchoolName) : DialogFragment() {

    private lateinit var binding: DialogFragmentSchoolBinding

    private val viewModel: SchoolViewModel by viewModel()

    companion object {
        const val TAG = "SchoolDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentSchoolBinding.inflate(layoutInflater)
        AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create().also { alertDialog ->
                viewModel.getSAT()
                return alertDialog
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupClickListener()
        setupObserver()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    private fun setupObserver() {

        // view subscribes for changes in ViewModel
        viewModel.onResultSAT().observe(this) { list ->
            //comparing two lists to get proper SAT score to proper school
            //some of them not in 2012 SAT results so its possible to no information provided
            val school =
                list.firstOrNull { it.dbn == schoolName.dbn }
            if (school != null) {
                binding.tvLog.visibility = View.GONE
                binding.tvTitle.text = school.schoolName
                binding.tvReading.text = "Reading avg. SAT: " + school.reading
                binding.tvWriting.text = "Writing avg. SAT: " + school.writing
                binding.tvMath.text = "Math avg. SAT: " + school.math
            } else {
                binding.tvLog.visibility = View.VISIBLE
                binding.tvLog.text = "No information provided"
                Timber.d("Wrong school dbn")
            }
        }
    }

    //logic for button close
    private fun setupClickListener() {
        binding.btnClose.setOnClickListener {
            safeDismiss()
        }
    }
}
