package com.ramazanmutlu.diarai

import android.content.Context
import androidx.room.Room
import com.ramazanmutlu.diarai.data.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = Database::class.java, name = "DiarAiDb"
    )
}