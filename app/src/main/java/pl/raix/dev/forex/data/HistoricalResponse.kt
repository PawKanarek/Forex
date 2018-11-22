package pl.raix.dev.forex.data

data class HistoricalResponse(val success: Boolean,
                              val timestamp: Int,
                              val historical: Boolean,
                              val base: String,
                              val date: String,
                              val rates: MutableList<Currency>
) {
}