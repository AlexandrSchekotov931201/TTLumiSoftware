package com.apshheko.ttlumisoftware.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apshheko.ttlumisoftware.R
import com.apshheko.ttlumisoftware.extencions.replaceFragment
import com.apshheko.ttlumisoftware.ui.fragments.RootFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        replaceFragment(RootFragment.create())
    }
}
