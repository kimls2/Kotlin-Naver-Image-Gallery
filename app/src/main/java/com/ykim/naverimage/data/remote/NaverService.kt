package com.ykim.naverimage.data.remote

import com.google.gson.GsonBuilder
import com.ykim.naverimage.BuildConfig
import com.ykim.naverimage.data.model.NaverImageResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ykim on 2017. 4. 11..
 */

interface NaverService {
    @GET("image")
    fun getImage(
            @Query("query") query: String, @Query("start") start: Int, @Query("display") display: Int, @Query("filter") filter: String): Observable<NaverImageResponse>

    object Factory {
        @JvmStatic fun makeNaverService(): NaverService {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BASIC
            else
                HttpLoggingInterceptor.Level.NONE

            val okHttpClient = OkHttpClient.Builder() //
                    .addInterceptor(logging)  //
                    .addInterceptor { chain ->
                        val request = chain.request()
                                .newBuilder()
                                .addHeader("X-Naver-Client-Id", API_ID)
                                .addHeader("X-Naver-Client-Secret", API_SECRET)
                                .build()
                        chain.proceed(request)
                    } //
                    .build()

            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()

            val retrofit = Retrofit.Builder() //
                    .baseUrl(API_URL) //
                    .client(okHttpClient) //
                    .addConverterFactory(GsonConverterFactory.create(gson)) //
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
                    .build()
            return retrofit.create(NaverService::class.java)
        }
    }

    companion object {
        val API_URL = "https://openapi.naver.com/v1/search/"
        val API_ID = "CTw8WwMTnGOgMNZs9Ma4"
        val API_SECRET = "9Fxd9KfgTi"
    }
}
