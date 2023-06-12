package com.captsone.padicure.di

import com.captsone.padicure.data.PadicureApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFireBase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore{
        return Firebase.firestore
    }
    @Provides
    @Singleton
    fun providePadicureApiService(retrofit: Retrofit): PadicureApiService {
        return retrofit.create(PadicureApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://home-dqo6vd65jq-et.a.run.app/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}