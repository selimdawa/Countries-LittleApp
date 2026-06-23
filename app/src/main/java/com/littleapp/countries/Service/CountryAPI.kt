package com.littleapp.countries.Service

import com.littleapp.countries.Unit.DATA
import com.littleapp.countries.Model.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountryAPI {
    @GET(DATA.COUNTRY_GSON)
    fun getCountries(): Single<List<Country>>
}