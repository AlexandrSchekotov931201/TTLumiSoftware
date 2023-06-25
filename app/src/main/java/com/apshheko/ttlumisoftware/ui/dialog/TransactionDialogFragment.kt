package com.apshheko.ttlumisoftware.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.apshheko.ttlumisoftware.R
import com.apshheko.ttlumisoftware.databinding.TransactionDialogFragmentBinding
import com.apshheko.ttlumisoftware.extencions.argsString
import com.apshheko.ttlumisoftware.extencions.getMethodIdFormInputData
import com.apshheko.ttlumisoftware.extencions.removeExtraZerosInInputData
import com.apshheko.ttlumisoftware.extencions.withArgs
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference

class TransactionDialogFragment : DialogFragment(R.layout.transaction_dialog_fragment) {

    private val binding by viewBinding(TransactionDialogFragmentBinding::bind)

    private var inputData = ""
    private var methodId = ""
    private var addressParam = ""
    private var uint256Param = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.RoundedDialog)
        inputData = argsString(EXTRA_INPUT_DATA_KEY)
        decodeInputData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        idMethodTextView.text = methodId
        addressTextView.text = addressParam
        unitTextView.text = uint256Param
        okButton.setOnClickListener {
            dismiss()
        }
    }

    private fun decodeInputData() {
        methodId = inputData.getMethodIdFormInputData()
        FunctionReturnDecoder.decode(
            inputData,
            listOf(
                TypeReference.makeTypeReference(ADDRESS_PARAM),
                TypeReference.makeTypeReference(UINT_PARAM)
            )
        ).apply {
            addressParam = (this[0].value as String).removeExtraZerosInInputData()
            uint256Param = this[1].value.toString()
        }
    }

    companion object {
        private const val ADDRESS_PARAM = "address"
        private const val UINT_PARAM = "uint256"
        private const val EXTRA_INPUT_DATA_KEY = "EXTRA_INPUT_DATA_KEY"

        fun show(
            inputData: String,
            fragmentManager: FragmentManager
        ) = TransactionDialogFragment()
            .withArgs(
                EXTRA_INPUT_DATA_KEY to inputData
            ).show(
                fragmentManager,
                TransactionDialogFragment::class.java.name
            )
    }
}
