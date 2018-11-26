package pl.raix.dev.forex.data

data class HistoricalResponse(
    val success: Boolean,
    val timestamp: Int,
    val historical: Boolean,
    val base: String,
    val date: String,
    private val rates: Map<String, Double>,
    val error: Map<String, String>
) {
    val currencyList: List<CurrencyModel> get() = rates.map { CurrencyModel(it.key, it.value, date) }
}