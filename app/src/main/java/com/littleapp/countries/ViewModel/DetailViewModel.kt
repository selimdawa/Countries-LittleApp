package com.littleapp.countries.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.littleapp.countries.Model.Country
import com.littleapp.countries.Service.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}