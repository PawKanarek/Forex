package pl.raix.dev.forex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.raix.dev.forex.data.Currency
import java.util.*

class MainViewModel : ViewModel() {

    private val currencyList : MutableLiveData<MutableList<Currency>> by lazy{
        MutableLiveData<MutableList<Currency>>()
    }

    fun getCurrency() = currencyList

    var currentDate: Date = Calendar.getInstance().time

    var isDataLoading: Boolean = false
}
