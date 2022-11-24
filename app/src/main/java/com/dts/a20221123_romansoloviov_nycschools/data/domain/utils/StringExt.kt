package com.dts.a20221123_romansoloviov_nycschools.data.domain.utils

fun String?.asException(): Exception {
    return Exception(this ?: "Msg is null")
}