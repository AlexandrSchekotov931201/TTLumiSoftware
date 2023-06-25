package com.apshheko.ttlumisoftware.extencions

import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Fragment.withArgs(vararg params: Pair<String, Any?>): Fragment = this.apply { arguments = bundleOf(*params) }

fun DialogFragment.withArgs(vararg params: Pair<String, Any?>): DialogFragment = this.apply { arguments = bundleOf(*params) }

fun Fragment.argsString(key: String): String = arguments?.getString(key, "") ?: ""
