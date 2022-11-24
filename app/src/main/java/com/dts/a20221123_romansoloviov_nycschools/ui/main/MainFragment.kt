package com.dts.a20221123_romansoloviov_nycschools.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.dts.a20221123_romansoloviov_nycschools.R
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolName
import com.dts.a20221123_romansoloviov_nycschools.databinding.FragmentMainBinding
import com.dts.a20221123_romansoloviov_nycschools.ui.main.adapter.MainAdapter
import com.dts.a20221123_romansoloviov_nycschools.ui.school.SchoolDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel : MainViewModel by viewModel()
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentMainBinding.bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSchoolName()
        setupAdapter()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.onResultName().observe(viewLifecycleOwner){ list ->

        }
    }

    private fun setupAdapter() {
        binding.rvSchools.adapter = adapter

            // view subscribes for changes in ViewModel
        viewModel.onResultName().observe(viewLifecycleOwner){ list ->
            adapter.items = list
        }
        adapter.onClickListener = ::onButtonClick
    }

    private fun onButtonClick( schoolName: SchoolName) {
        SchoolDialogFragment(schoolName).show(
            requireActivity().supportFragmentManager,
            SchoolDialogFragment.TAG
        )
    }
}
