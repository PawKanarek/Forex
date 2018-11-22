package pl.raix.dev.forex.adapters

import androidx.recyclerview.widget.DiffUtil
import pl.raix.dev.forex.data.Currency

class CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>(){
    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}