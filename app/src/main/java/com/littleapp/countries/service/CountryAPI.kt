package com.littleapp.countries.service

import com.littleapp.countries.utils.DATA
import com.littleapp.countries.model.Country
import retrofit2.http.GET

interface CountryAPI {
    @GET(DATA.COUNTRY_GSON)
    suspend fun getCountries(): List<Country>
}