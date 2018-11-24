package pl.raix.dev.forex.adapters

import androidx.recyclerview.widget.DiffUtil
import pl.raix.dev.forex.data.CurrencyModel

class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyModel>(){
    override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}