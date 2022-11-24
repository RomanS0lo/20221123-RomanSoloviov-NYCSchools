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
import com.dts.a20221123_romansoloviov_nycschools.databinding.DialogFragmentSchoolBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SchoolDialogFragment(private val schoolName: String) : DialogFragment() {

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
        viewModel.onResultSAT().observe(this) { list ->
            val school =
                list.firstOrNull { it.schoolName == schoolName }
            if (school != null) {
                binding.tvReading.text = "Reading avg. SAT: " + school.reading
                binding.tvWriting.text = "Writing avg. SAT: " + school.writing
                binding.tvMath.text = "Math avg. SAT: " + school.math
            } else {
                Timber.d("Wrong school name")
            }
        }
    }

    private fun setupClickListener() {
        binding.btnClose.setOnClickListener {
            safeDismiss()
        }
    }
}
