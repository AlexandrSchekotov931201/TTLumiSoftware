package com.apshheko.ttlumisoftware.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.apshheko.ttlumisoftware.App
import com.apshheko.ttlumisoftware.R
import com.apshheko.ttlumisoftware.databinding.TransactionsFragmentBinding
import com.apshheko.ttlumisoftware.extencions.argsString
import com.apshheko.ttlumisoftware.extencions.isEmptyInputData
import com.apshheko.ttlumisoftware.extencions.withArgs
import com.apshheko.ttlumisoftware.models.ApiResult
import com.apshheko.ttlumisoftware.network.repositories.interfaces.EtherscanRepository
import com.apshheko.ttlumisoftware.ui.adapter.TransactionsAdapter
import com.apshheko.ttlumisoftware.ui.dialog.TransactionDialogFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionsFragment : CoreFragment(R.layout.transactions_fragment) {

    @Inject
    lateinit var etherscanRepository: EtherscanRepository

    private val binding by viewBinding(TransactionsFragmentBinding::bind)
    private val transactionsAdapter = TransactionsAdapter {
        if (it.isEmptyInputData()) {
            TransactionDialogFragment.show(it, childFragmentManager)
        } else {
            showErrorAlert()
        }
    }

    private var address: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as App).appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        address = argsString(EXTRA_ADDRESS_KEY)
        transactionsAdapter.setAddress(address)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
    }

    private fun initViews() = with(binding) {
        transactions.adapter = this@TransactionsFragment.transactionsAdapter
        retryLoadButton.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            showError(false)
            showLoader(true)
            val result = etherscanRepository.getTransactions(
                address, START_BLOCK, END_BLOCK, PAGE_BLOCK, COUNT_TRANSACTIONS, SORT,
            )
            when (result) {
                is ApiResult.Success -> {
                    result.data.transactions.takeIf { it.isNotEmpty() }?.let {
                        transactionsAdapter.setData(result.data.transactions)
                    } ?: showEmptyError()
                }

                is ApiResult.Error -> {
                    transactionsAdapter.clear()
                    showFatalError()
                }
            }
            showLoader(false)
        }
    }

    private fun showLoader(isVisible: Boolean) = with(binding) {
        transactions.isVisible = !isVisible
        loader.isVisible = isVisible
    }

    private fun showEmptyError() = with(binding) {
        errorMessage.setText(R.string.empty_transactions_error)
        showError(true)
    }

    private fun showFatalError() = with(binding) {
        errorMessage.setText(R.string.load_transactions_error)
        showError(true)
    }

    private fun showError(isVisible: Boolean) = with(binding) {
        errorMessage.isVisible = isVisible
        retryLoadButton.isVisible = isVisible
    }

    private fun showErrorAlert() {
        AlertDialog.Builder(context)
            .setTitle(R.string.error_title)
            .setMessage(R.string.transaction_data_is_missing)
            .setPositiveButton(R.string.it_is_clear) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    companion object {
        private const val START_BLOCK = "0"
        private const val END_BLOCK = "latest"
        private const val PAGE_BLOCK = "1"
        private const val COUNT_TRANSACTIONS = "100"
        private const val SORT = "desc"
        private const val EXTRA_ADDRESS_KEY = "EXTRA_ADDRESS_KEY"

        fun create(
            address: String
        ) = TransactionsFragment().withArgs(
            EXTRA_ADDRESS_KEY to address
        )
    }
}
