package com.ykim.naverimage.data

import com.ykim.naverimage.data.model.ImageSizeFilter
import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.data.remote.NaverService
import io.reactivex.Observable
import org.apache.commons.collections4.CollectionUtils
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ykim on 2017. 7. 3..
 */
@Singleton class DataManager @Inject constructor(private val naverService: NaverService) {

    fun getImage(query: String, start: Int): Observable<MutableList<NaverImage>> {
        return naverService.getImage(query, start, 20, ImageSizeFilter.large.name)
                .filter({ CollectionUtils.isNotEmpty(it.items) })
                .map { it.items }
    }
}