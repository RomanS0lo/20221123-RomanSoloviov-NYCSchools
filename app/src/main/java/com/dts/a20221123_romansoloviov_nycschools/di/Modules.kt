package com.dts.a20221123_romansoloviov_nycschools.di

import com.dts.a20221123_romansoloviov_nycschools.data.domain.ApiService
import com.dts.a20221123_romansoloviov_nycschools.data.domain.retrofit.RestApiService
import com.dts.a20221123_romansoloviov_nycschools.ui.main.MainViewModel
import com.dts.a20221123_romansoloviov_nycschools.ui.school.SchoolViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Endpoints {
    const val SCHOOLS_BASE_URL: String = "https://data.cityofnewyork.us/resource/s3k6-pzi2.json"
    const val SAT_BASE_URL: String = "https://data.cityofnewyork.us/resource/"
}

val app = module {
    single { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
    single { RestApiService(get(), get()) }
}

val viewModels = module {
    viewModel { MainViewModel() }
    viewModel { SchoolViewModel(get()) }
}
