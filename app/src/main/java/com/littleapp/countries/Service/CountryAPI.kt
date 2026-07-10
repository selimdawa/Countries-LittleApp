package com.littleapp.countries.Service

import com.littleapp.countries.utils.DATA
import com.littleapp.countries.Model.Country
import retrofit2.http.GET

interface CountryAPI {
    @GET(DATA.COUNTRY_GSON)
    suspend fun getCountries(): List<Country>
}