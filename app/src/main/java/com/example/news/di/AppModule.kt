package com.example.news.di

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news.data.database.NewsDao
import com.example.news.data.database.NewsDatabase
import com.example.news.data.remote.NewsApi
import com.example.news.data.repositories.localusermanager.LocalUserManager
import com.example.news.data.repositories.localusermanager.LocalUserManagerImpl
import com.example.news.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager {
        return LocalUserManagerImpl(context);

    }

    @Apikey
    @Provides
    fun getApikey(@ApplicationContext context: Context): String {
        return context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("API_KEY", "")
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Util.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun getNewsDatabase(@ApplicationContext context: Context): NewsDatabase{
        return Room.databaseBuilder(context = context, klass = NewsDatabase::class.java, name = "NewsArticleDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getNewsDao(newsDatabase: NewsDatabase): NewsDao{
        return newsDatabase.getNewsDao()
    }

}