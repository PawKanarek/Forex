package pl.raix.dev.forex.data

data class HistoricalResponse(
    val success: Boolean,
    val timestamp: Int,
    val historical: Boolean,
    val base: String,
    val date: String,
    private val rates: Map<String, Double>
) {
    val currencyList: List<Currency> get() =  rates.map { Currency(it.key, it.value) }
}