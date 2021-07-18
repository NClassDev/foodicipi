package com.nclassdev.foodicipi.application.injection

import com.nclassdev.foodicipi.data.source.remote.network.SpoonacularApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private val hostName = "api.spoonacular.com"

    val certificatePinner = CertificatePinner.Builder()
        .add(hostName, "sha256/DjBNmOWrmE91DLaH6gk+96MMBNjNs4/cbGxgvWLZi18=")
        .add(hostName, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
        .add(hostName, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
        .build()

    val requestInterceptor = Interceptor { chain ->
        val httpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apiKey", "7417fd2b071744339a8e4fe668fd5a05")
            .build()
        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()
        return@Interceptor chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .readTimeout(120, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }



    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(SpoonacularApiService::class.java)


}