package com.ranit.android.lexicon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ranit.android.lexicon.view.DisplayWordActivityViewImpl

class DisplayWordActivity : AppCompatActivity() {
    private lateinit var displayWordActivityViewInstance: DisplayWordActivityViewImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayWordActivityViewInstance = DisplayWordActivityViewImpl(this, null, intent, this)

        setContentView(displayWordActivityViewInstance.rootView)
        displayWordActivityViewInstance.initView()
    }

    override fun onResume() {
        super.onResume()
        displayWordActivityViewInstance.bindDataToView()
    }
}