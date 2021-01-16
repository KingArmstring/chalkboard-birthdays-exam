package com.chalkboardexam.birthdays.di

import com.chalkboardexam.birthdays.applevel.ChalkBoardApplication
import com.chalkboardexam.birthdays.utils.Constants
import com.chalkboardexam.birthdays.utils.NetworkUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
//        .excludeFieldsWithoutExposeAnnotation()
// we can it if we need only @Expose fields to be parsed from the response which is the case in many
// projects because we usually have fields we are not interested in and the objects returned may
// differ from those in our model data class, and that's why I have mappers
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideHttpClient(
        interceptor: HttpLoggingInterceptor,
        cache: Cache,
        @Named("NETWORK_INTERCEPTOR") networkInterceptor: Interceptor,
        @Named("OFFLINE_INTERCEPTOR") offlineInterceptor: Interceptor
    ) : OkHttpClient {
        return OkHttpClient
            .Builder()
            .cache(cache)
            .addInterceptor(interceptor)//optional in case we need to show logs.
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(offlineInterceptor)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCache(application: ChalkBoardApplication) : Cache {
        val cacheSize : Long = 6 * 1024 * 1046 // specifying 6 MB for caching.
        return Cache(File(application.cacheDir, "network_cache"), cacheSize)
    }

    @JvmStatic
    @Singleton
    @Provides
    @Named("OFFLINE_INTERCEPTOR")
    fun provideOfflineInterceptor(application: ChalkBoardApplication) : Interceptor {
        val HEADER_PRAGMA = "Pragma"
        val HEADER_CACHE_CONTROL = "Cache-Control"

        return Interceptor { chain ->
            var request = chain.request()
            if (!NetworkUtils.hasNetworkAvailable(application)) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(5, TimeUnit.DAYS)//cache will remain for 5 days
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
            chain.proceed(request)
        }
    }

    @JvmStatic
    @Singleton
    @Provides
    @Named("NETWORK_INTERCEPTOR")
    fun provideNetworkInterceptor() : Interceptor {
        val HEADER_PRAGMA = "Pragma"
        val HEADER_CACHE_CONTROL = "Cache-Control"

        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)//no network request will be made after one complete minute passes after the previous request.
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }
}