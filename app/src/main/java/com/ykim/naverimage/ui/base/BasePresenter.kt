package com.ykim.naverimage.ui.base

import io.reactivex.disposables.CompositeDisposable


/**
 * Created by ykim on 2017. 4. 18..
 */
abstract class BasePresenter<T : BaseMvp.View> : BaseMvp.Presenter<T> {

    protected val disposables = CompositeDisposable()
    protected var _view: T? = null
    var isViewAttached: Boolean = _view != null
    val view: T
        get() {
            return _view ?: throw MvpViewNotAttachedException()
        }

    override fun onAttach(view: T) {
        _view = view
    }

    override fun onDetach() {
        disposables.clear()
        _view = null
    }

    class MvpViewNotAttachedException : RuntimeException(
            "Please call Presenter.attachView(MvpView) before requesting data to the Presenter")

}