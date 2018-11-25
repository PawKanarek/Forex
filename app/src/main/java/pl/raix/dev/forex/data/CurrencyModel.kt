package pl.raix.dev.forex.data

import android.os.Parcel
import android.os.Parcelable

data class CurrencyModel(
    val name: String,
    val value: Double,
    val dateFrom: String?
) :Parcelable {
    constructor(name: String, value: Double) : this(name, value, null)

    var currencyModelType: CurrencyModelType = CurrencyModelType.Currency

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(value)
        parcel.writeString(dateFrom)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrencyModel> {
        override fun createFromParcel(parcel: Parcel): CurrencyModel {
            return CurrencyModel(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyModel?> {
            return arrayOfNulls(size)
        }
    }
}