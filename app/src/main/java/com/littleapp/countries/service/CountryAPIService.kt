package com.littleapp.countries.service

import com.littleapp.countries.model.Country
import com.littleapp.countries.utils.DATA
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(DATA.BASE_URL_COUNTRY)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    suspend fun getData(): List<Country> {
        return api.getCountries()
    }
}