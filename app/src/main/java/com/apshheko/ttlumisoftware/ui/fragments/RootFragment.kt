package com.apshheko.ttlumisoftware.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.apshheko.ttlumisoftware.R
import com.apshheko.ttlumisoftware.databinding.RootFragmentBinding
import com.apshheko.ttlumisoftware.extencions.replaceFragment
import org.web3j.crypto.WalletUtils

class RootFragment : Fragment(R.layout.root_fragment) {

    private val binding by viewBinding(RootFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        addressEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // not used
            }

            override fun afterTextChanged(s: Editable?) {
                binding.loadTransactionsButton.isEnabled = WalletUtils.isValidAddress(s.toString())
            }
        })
        loadTransactionsButton.setOnClickListener {
            replaceFragment(TransactionsFragment.create(binding.addressEdittext.text.toString()))
        }
    }

    companion object {
        fun create() = RootFragment()
    }
}
