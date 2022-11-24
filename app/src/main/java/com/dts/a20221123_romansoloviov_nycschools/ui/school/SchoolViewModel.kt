package com.dts.a20221123_romansoloviov_nycschools.ui.school

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dts.a20221123_romansoloviov_nycschools.data.domain.OnApiResultCallback
import com.dts.a20221123_romansoloviov_nycschools.data.domain.retrofit.RestApiService
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolSAT
import timber.log.Timber

class SchoolViewModel(
    private val apiService: RestApiService
) : ViewModel() {

    private val satLiveData = MutableLiveData<List<SchoolSAT>>()

    fun onResultSAT(): LiveData<List<SchoolSAT>> = satLiveData

    fun getSAT() {
        apiService.getSAT(onResult = object : OnApiResultCallback<List<SchoolSAT>> {
            override fun onSuccess(response: List<SchoolSAT>) {
                satLiveData.postValue(response)
            }

            override fun onFail(exception: Exception, code: Int) {
                Timber.e(exception)
            }
        })
    }
}
