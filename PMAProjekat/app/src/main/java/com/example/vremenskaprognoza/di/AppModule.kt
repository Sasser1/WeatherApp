package com.example.vremenskaprognoza.di

import android.content.Context
import androidx.room.Room
import com.example.vremenskaprognoza.WeatherApplication
import com.example.vremenskaprognoza.searchCities.data.dataSource.CityDao
import com.example.vremenskaprognoza.searchCities.data.dataSource.CityDatabase
import com.example.vremenskaprognoza.searchCities.data.dataSource.GeoApi
import com.example.vremenskaprognoza.searchCities.data.repository.DatabaseCityRepositoryImpl
import com.example.vremenskaprognoza.searchCities.data.repository.NetworkCityRepositoryImpl
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository
import com.example.vremenskaprognoza.searchCities.domain.repository.NetworkCityRepository
import com.example.vremenskaprognoza.searchCities.domain.useCase.CitySearchUseCases
import com.example.vremenskaprognoza.searchCities.domain.useCase.GetDatabaseCities
import com.example.vremenskaprognoza.searchCities.domain.useCase.GetFavoriteCities
import com.example.vremenskaprognoza.searchCities.domain.useCase.GetNetworkCities
import com.example.vremenskaprognoza.searchCities.domain.useCase.InsertCities
import com.example.vremenskaprognoza.searchCities.domain.useCase.UpdateCity
import com.example.vremenskaprognoza.weather.data.dataSource.WeatherApi
import com.example.vremenskaprognoza.weather.data.repository.WeatherRepositoryImpl
import com.example.vremenskaprognoza.weather.domain.repository.WeatherRepository
import com.example.vremenskaprognoza.weather.domain.useCase.GetCurrentWeather
import com.example.vremenskaprognoza.weather.domain.useCase.GetDailyWeather
import com.example.vremenskaprognoza.weather.domain.useCase.GetHourlyWeather
import com.example.vremenskaprognoza.weather.domain.useCase.WeatherUseCases
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        /*
         *   Get data from cache when offline
         */
        fun offlineInterceptor(): Interceptor {
            return Interceptor { chain ->
                var request: Request = chain.request()
                if (WeatherApplication.hasNetwork() == false) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(5, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                chain.proceed(request)
            }
        }

        /*
         *   Get data from network when online
         */
        fun networkInterceptor(): Interceptor {
            return Interceptor { chain ->
                val response: Response = chain.proceed(chain.request())
                val cacheControl = CacheControl.Builder()
                    .maxAge(20, TimeUnit.MINUTES)
                    .build()
                response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }

        // Creating cache
        val cacheMaxSize: Long = 5 * 1024 * 1024  // 5 MiB
        val cache = Cache(File(context.cacheDir, "weather-cache"), cacheMaxSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(networkInterceptor())
            .addInterceptor(offlineInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideCityDao(@ApplicationContext context: Context): CityDao {
        return Room.databaseBuilder(
            context = context,
            klass = CityDatabase::class.java,
            name = "weather-database"
        )
            .build()
            .cityDao()
    }

    @Provides
    @Singleton
    fun provideGeoApi(okHttpClient: OkHttpClient): GeoApi {
        val baseurl = "https://geocoding-api.open-meteo.com/v1/"
        val retrofit = createRetrofit(okHttpClient, baseurl)
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideNetworkCityRepository(geoApi: GeoApi): NetworkCityRepository {
        return NetworkCityRepositoryImpl(geoApi)
    }

    @Provides
    @Singleton
    fun provideDatabaseCityRepository(cityDao: CityDao): DatabaseCityRepository {
        return DatabaseCityRepositoryImpl(cityDao)
    }

    @Provides
    @Singleton
    fun provideCitySearchUseCases(
        networkCityRepository: NetworkCityRepository,
        databaseCityRepository: DatabaseCityRepository,
        @ApplicationContext context: Context
    ): CitySearchUseCases {
        return CitySearchUseCases(
            getDatabaseCities = GetDatabaseCities(databaseCityRepository),
            getNetworkCities = GetNetworkCities(networkCityRepository, context),
            getFavoriteCities = GetFavoriteCities(databaseCityRepository),
            insertCities = InsertCities(databaseCityRepository),
            updateCity = UpdateCity(databaseCityRepository)
        )
    }

    @Provides
    @Singleton
    fun provideWeatherApi(okHttpClient: OkHttpClient): WeatherApi {
        val baseurl = "https://api.open-meteo.com/v1/"
        val retrofit = createRetrofit(okHttpClient, baseurl)
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideWeatherUseCases(
        weatherRepository: WeatherRepository
    ): WeatherUseCases {
        return WeatherUseCases(
            getCurrentWeather = GetCurrentWeather(weatherRepository),
            getHourlyWeather = GetHourlyWeather(weatherRepository),
            getDailyWeather = GetDailyWeather(weatherRepository)
        )
    }

    private fun createRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val contentType: MediaType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}