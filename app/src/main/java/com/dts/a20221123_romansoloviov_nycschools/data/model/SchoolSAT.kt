package com.dts.a20221123_romansoloviov_nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SchoolSAT(
    val dbn :String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSAT: String,
    @SerializedName("sat_critical_reading_avg_score")
    val reading: String,
    @SerializedName("sat_math_avg_score")
    val math: String,
    @SerializedName("sat_writing_avg_score")
    val writing: String
)
