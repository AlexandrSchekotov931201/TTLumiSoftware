package com.apshheko.ttlumisoftware.ui.fragments

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.apshheko.ttlumisoftware.extencions.popBackStack

abstract class CoreFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    private var backPressedCallback: OnBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            popBackStack()
        }
    }
}
