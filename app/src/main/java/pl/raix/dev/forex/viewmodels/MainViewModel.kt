package pl.raix.dev.forex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.raix.dev.forex.data.Currency

class MainViewModel : ViewModel() {

    private val currencyList : MutableLiveData<List<Currency>> by lazy{
        MutableLiveData<List<Currency>>()
    }

    fun getCurrency() = currencyList
}
