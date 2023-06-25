package com.apshheko.ttlumisoftware.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apshheko.ttlumisoftware.R
import com.apshheko.ttlumisoftware.databinding.TransactionItemBinding
import com.apshheko.ttlumisoftware.extencions.timestampToDate
import com.apshheko.ttlumisoftware.extencions.toEHT
import com.apshheko.ttlumisoftware.extencions.toFormat
import com.apshheko.ttlumisoftware.models.EnumDateFormat
import com.apshheko.ttlumisoftware.models.Transaction

class TransactionsAdapter(
    private val onClickItemCallback: (String) -> Unit
) : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    private var items = mutableListOf<Transaction>()
    private var address = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(address, view, onClickItemCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setAddress(address: String) {
        this.address = address
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newItems: List<Transaction>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val address: String,
        private val binding: TransactionItemBinding,
        private val onClickItemCallback: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) = with(binding) {
            val date = item.timestamp.timestampToDate()
            val isOutgoingTransaction = item.from.lowercase() == address.lowercase()
            dateMonthTextView.text = date.toFormat(EnumDateFormat.MonthDayFormat)
            dateTimeTextView.text = date.toFormat(EnumDateFormat.TimeFormat)
            fromValueTextView.text = item.from
            toValueTextView.text = item.to
            valueTextView.text = item.value.toEHT()

            if (isOutgoingTransaction) {
                statusTextView.setText(R.string.transaction_status_out)
                statusTextView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.textColorOut
                    )
                )
                statusImageView.backgroundTintList =
                    ContextCompat.getColorStateList(itemView.context, R.color.backgroundColorOut)
            } else {
                statusTextView.setText(R.string.transaction_status_in)
                statusTextView.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.textColorIn
                    )
                )
                statusImageView.backgroundTintList =
                    ContextCompat.getColorStateList(itemView.context, R.color.backgroundColorIn)
            }

            root.setOnClickListener {
                onClickItemCallback.invoke(item.input)
            }
        }
    }
}
