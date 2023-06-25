package com.apshheko.ttlumisoftware.extencions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.apshheko.ttlumisoftware.R

fun FragmentActivity.replaceFragment(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true
) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(containerId, target, target.javaClass.name)
    if (addToBackStack) transaction.addToBackStack(target.javaClass.name)
    transaction.commit()
}

fun Fragment.replaceFragment(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.setCustomAnimations(
        R.anim.nav_enter,
        R.anim.nav_exit,
        R.anim.nav_pop_enter,
        R.anim.nav_pop_exit
    )
    transaction.replace(containerId, target, target.javaClass.name)
    if (addToBackStack) transaction.addToBackStack(target.javaClass.name)
    transaction.commit()
}
