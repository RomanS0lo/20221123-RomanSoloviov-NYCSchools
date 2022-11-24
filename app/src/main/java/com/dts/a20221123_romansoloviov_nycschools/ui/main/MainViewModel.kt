package com.dts.a20221123_romansoloviov_nycschools.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dts.a20221123_romansoloviov_nycschools.data.domain.OnApiResultCallback
import com.dts.a20221123_romansoloviov_nycschools.data.domain.retrofit.RestApiService
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolName
import timber.log.Timber

class MainViewModel(private val api: RestApiService) : ViewModel() {


    private val nameLiveData = MutableLiveData<List<SchoolName>>()

    fun onResultName(): LiveData<List<SchoolName>> = nameLiveData

    fun getSchoolName() {
        api.getSchoolName(onResult = object : OnApiResultCallback<List<SchoolName>> {
            override fun onSuccess(response: List<SchoolName>) {
                nameLiveData.postValue(response)
            }

            override fun onFail(exception: Exception, code: Int) {
                Timber.d(exception)
            }

        })
    }
}