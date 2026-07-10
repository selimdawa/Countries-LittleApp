package com.littleapp.countries.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.littleapp.countries.Model.Country
import com.littleapp.countries.Service.CountryAPIService
import com.littleapp.countries.Service.CountryDatabase
import com.littleapp.countries.Util.CustomSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val countryApiService = CountryAPIService()

    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
    }

    private fun getDataFromSQLite() {
        viewModelScope.launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Countries from SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataFromAPI() {
        countryLoading.value = true
        viewModelScope.launch {
            try {
                val list = withContext(Dispatchers.IO) {
                    countryApiService.getData()
                }
                storeInSQLite(list)
                Toast.makeText(getApplication(), "Countries from API", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                countryError.value = true
                countryLoading.value = false
                e.printStackTrace()
            }
        }
    }

    private fun showCountries(countryL: List<Country>) {
        countries.value = countryL
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(list: List<Country>) {
        viewModelScope.launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray())

            list.forEachIndexed { index, country ->
                country.uuid = listLong[index].toInt()
            }

            customSharedPreferences.saveTime(System.nanoTime())
            showCountries(list)
        }
    }
}