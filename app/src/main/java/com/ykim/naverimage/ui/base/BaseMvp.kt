package com.ykim.naverimage.ui.base

interface BaseMvp {

    interface View {
        fun showLoading(show: Boolean)
        fun showError(message: String?)
    }

    interface Presenter<in T> {
        fun onAttach(view: T)
        fun onDetach()
    }
}