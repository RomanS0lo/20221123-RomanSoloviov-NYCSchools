package com.dts.a20221123_romansoloviov_nycschools.data.domain

//Callback to handle response
interface OnApiResultCallback<T> {

    fun onSuccess(response: T)

    fun onFail(exception: Exception, code: Int)
}
