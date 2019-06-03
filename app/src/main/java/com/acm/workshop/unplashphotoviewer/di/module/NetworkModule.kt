package com.acm.workshop.unplashphotoviewer.di.module

import com.acm.workshop.remote.api.PhotoApi
import com.acm.workshop.remote.dto.toPhotoEntity
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {


    @Provides
    fun provideOkHttpClient() = OkHttpClient()


//    @Provides
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .addInterceptor { chain ->
//                val request = chain.request()
//                    .newBuilder()
//                    .addHeader("client_id", "f49cf4050a7f98026dc1e6cec7033e151d936de8435faba8e637aa0d10cc6bd1")
//                    .build()
//                chain.proceed(request)
//            }.build()
//    }

    @Provides
    fun provideRxJavaAdapter() = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideGsonConverter() = GsonConverterFactory.create()


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PhotoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    @Provides
    fun providePhotoApi(retrofit: Retrofit) = retrofit.create(PhotoApi::class.java)

}
