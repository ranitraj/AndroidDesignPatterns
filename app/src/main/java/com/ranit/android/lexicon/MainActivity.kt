package com.ranit.android.lexicon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.ranit.android.lexicon.view.MainActivityViewImpl

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewImpl : MainActivityViewImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewImpl = MainActivityViewImpl(this, null)

        setContentView(mainActivityViewImpl.rootView)
        mainActivityViewImpl.initView()
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewImpl.bindDataToView()
    }
}