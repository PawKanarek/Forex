package pl.raix.dev.forex.data

data class CurrencyModel(
    val name: String,
    val value: Double,
    val dateFrom: String?
) {
    constructor(name: String, value: Double) : this(name, value, null)

    var currencyModelType: CurrencyModelType = CurrencyModelType.Currency
}