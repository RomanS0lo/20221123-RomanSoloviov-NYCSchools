package com.dts.a20221123_romansoloviov_nycschools.data.domain.retrofit

import com.dts.a20221123_romansoloviov_nycschools.data.domain.ApiService
import com.dts.a20221123_romansoloviov_nycschools.data.domain.OnApiResultCallback
import com.dts.a20221123_romansoloviov_nycschools.data.domain.utils.asException
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolName
import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolSAT
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.HttpURLConnection

class RestApiService(
    private val apiService: ApiService
) {

    fun getSchoolName(onResult: OnApiResultCallback<List<SchoolName>>) {
        apiService.getSchoolName().enqueue(
            object : Callback<List<SchoolName>> {
                override fun onResponse(
                    call: Call<List<SchoolName>>,
                    response: Response<List<SchoolName>>
                ) {

                    if (response.isSuccessful) {
                        val responseName = response.body()
                        if (responseName != null) onResult.onSuccess(responseName)
                        else onResult.onFail("Name is null ".asException(), response.code())
                    } else {
                        response.errorBody()?.string()?.let { msg ->
                            try {
                                onResult.onFail(msg.asException(), response.code())
                            } catch (e: Exception) {
                                onResult.onFail(e, response.code())
                            }
                        } ?: onResult.onFail("Name data is null".asException(), response.code())
                    }
                }

                override fun onFailure(call: Call<List<SchoolName>>, t: Throwable) {
                    Timber.e(t)
                    onResult.onFail(t.message.asException(), HttpURLConnection.HTTP_BAD_REQUEST)
                }
            }
        )
    }

    fun getSAT(
        onResult: OnApiResultCallback<List<SchoolSAT>>
    ) {
        apiService.getSAT().enqueue(
            object : Callback<List<SchoolSAT>> {
                override fun onResponse(
                    call: Call<List<SchoolSAT>>,
                    response: Response<List<SchoolSAT>>
                ) {
                    if (response.isSuccessful) {
                        val responseSAT = response.body()
                        if (responseSAT != null) onResult.onSuccess(responseSAT)
                        else onResult.onFail("SAT is null ".asException(), response.code())
                    } else {
                        response.errorBody()?.string()?.let { msg ->
                            try {
                                onResult.onFail(msg.asException(), response.code())
                            } catch (e: Exception) {
                                onResult.onFail(e, response.code())
                            }
                        } ?: onResult.onFail("SAT data is null".asException(), response.code())
                    }
                }

                override fun onFailure(call: Call<List<SchoolSAT>>, t: Throwable) {
                    Timber.e(t)
                    onResult.onFail(t.message.asException(), HttpURLConnection.HTTP_BAD_REQUEST)
                }
            }
        )
    }
}

