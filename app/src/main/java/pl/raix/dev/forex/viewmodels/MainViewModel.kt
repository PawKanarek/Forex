package pl.raix.dev.forex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.raix.dev.forex.data.CurrencyModel
import java.util.*

class MainViewModel : ViewModel() {

    private val currencyList : MutableLiveData<MutableList<CurrencyModel>> by lazy{
        MutableLiveData<MutableList<CurrencyModel>>()
    }

    fun getCurrency() = currencyList

    var currentDate: Date = Calendar.getInstance().time

    var isDataLoading: Boolean = false
}
