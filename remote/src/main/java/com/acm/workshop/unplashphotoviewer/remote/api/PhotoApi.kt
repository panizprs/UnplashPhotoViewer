package com.acm.workshop.remote.api

import com.acm.workshop.remote.dto.UnsplashPhoto
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("collections/317099/photos")
    fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("order_by") orderBy : String
    ): Single<Response<List<UnsplashPhoto>>>

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val ORDER_BY_LATEST = "latest"
        const val ORDER_BY_OLDEST = "oldest"
        const val ORDER_BY_POPULAR = "popular"
    }
}