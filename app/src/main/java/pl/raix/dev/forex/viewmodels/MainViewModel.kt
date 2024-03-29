package pl.raix.dev.forex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.raix.dev.forex.data.CurrencyModel
import java.util.Date
import java.util.Calendar

class MainViewModel : ViewModel() {

    private val currencyList: MutableLiveData<MutableList<CurrencyModel>> by lazy {
        MutableLiveData<MutableList<CurrencyModel>>()
    }

    fun getCurrency() = currencyList

    var currentDate: Date = Calendar.getInstance().time

    var isDataLoading: Boolean = false
}
