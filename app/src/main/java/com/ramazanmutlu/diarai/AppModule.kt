package com.ramazanmutlu.diarai

import android.content.Context
import androidx.room.Room
import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.OpenRouterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = Database::class.java, name = "DiarAiDb"
    ).build()


    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("https://openrouter.ai/api/v1").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    @Provides
    fun provideOpenRouterService(retrofit: Retrofit) =
        retrofit.create(OpenRouterService::class.java)
}