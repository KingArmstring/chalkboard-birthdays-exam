package com.chalkboardexam.birthdays.di.birthdays

import com.chalkboardexam.birthdays.businesslogic.data.network.abstraction.BirthdaysNetworkDataSource
import com.chalkboardexam.birthdays.businesslogic.data.network.implementation.BirthdaysNetworkDataSourceImpl
import com.chalkboardexam.birthdays.framework.data.network.abstraction.BirthdaysService
import com.chalkboardexam.birthdays.framework.data.network.api.ChalkboardApi
import com.chalkboardexam.birthdays.framework.data.network.implementation.BirthdaysServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object BirthdaysModule {
    @JvmStatic
    @BirthdaysScope
    @Provides
    fun provideApi(
        retrofitBuilder: Retrofit.Builder,
        client: OkHttpClient
    ) : ChalkboardApi {
        return retrofitBuilder
            .client(client)
            .build()
            .create(ChalkboardApi::class.java)
    }

    @JvmStatic
    @BirthdaysScope
    @Provides
    fun provideApiService(api: ChalkboardApi) : BirthdaysService {
        return BirthdaysServiceImpl(api)
    }

    @JvmStatic
    @BirthdaysScope
    @Provides
    fun provideNetworkDataSource(birthDaysService: BirthdaysService) : BirthdaysNetworkDataSource {
        return BirthdaysNetworkDataSourceImpl(birthDaysService)
    }
}