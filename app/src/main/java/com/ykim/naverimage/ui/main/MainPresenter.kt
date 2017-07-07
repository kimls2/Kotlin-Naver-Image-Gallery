package com.ykim.naverimage.ui.main

import com.ykim.naverimage.data.DataManager
import com.ykim.naverimage.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ykim on 2017. 7. 5..
 */
class MainPresenter
@Inject constructor(val dataManager: DataManager) : BasePresenter<MainMvp.View>(), MainMvp.Presenter {
    override fun loadImage(searchQuery: String, start: Int) {
        view.showLoading(true)
        disposables.add(dataManager.getImage(searchQuery, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view.showImage(it)
                        }
                        , onComplete = {
                    view.showLoading(false)
                }, onError = {
                    view.showError(it.message)
                })

        )
    }
}