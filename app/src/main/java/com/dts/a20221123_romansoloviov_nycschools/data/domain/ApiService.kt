package com.dts.a20221123_romansoloviov_nycschools.data.domain

import com.dts.a20221123_romansoloviov_nycschools.data.model.SchoolSAT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("school_name")
     fun getSchools(): List<String>

     @GET("f9bf-2cp4.json")
     fun getSAT(): Call<List<SchoolSAT>>
}