package com.example.roomsolo.di

import android.content.Context
import androidx.room.Room
import com.example.roomsolo.tools.AppDatabase
import com.example.roomsolo.tools.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_bd"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): Dao = appDatabase.noteDao()
}