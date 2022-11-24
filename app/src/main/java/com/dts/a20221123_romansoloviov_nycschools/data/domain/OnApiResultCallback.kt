package com.dts.a20221123_romansoloviov_nycschools.data.domain

interface OnApiResultCallback<T> {

    fun onSuccess(response: T)

    fun onFail(exception: Exception, code: Int)
}
