package com.ranit.android.lexicon

/**
 * View and Presenter have 1-1 mapping
 * This mapping is defined by a Contract
 */

interface MainActivityContract {

    interface MainActivityView : BaseView<MainActivityPresenter> {

    }

    interface MainActivityPresenter : BasePresenter {

    }
}