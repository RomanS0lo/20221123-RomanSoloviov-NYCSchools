package com.dts.a20221123_romansoloviov_nycschools.data.model

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("msg", alternate = ["message"]) val msg: String
)
