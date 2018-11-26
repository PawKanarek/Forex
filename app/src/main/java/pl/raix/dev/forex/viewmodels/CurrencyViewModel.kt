package pl.raix.dev.forex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.raix.dev.forex.data.CurrencyModel

class CurrencyViewModel : ViewModel() {
    val currencyModel: MutableLiveData<CurrencyModel> by lazy { MutableLiveData<CurrencyModel>() }
}
