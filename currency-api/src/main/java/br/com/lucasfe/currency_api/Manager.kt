package br.com.lucasfe.currency_api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val manager = DI {

    bind<Interceptor>(tag = "accessKey") with provider {
        // Add CurrencyLayer API key to every call
        Interceptor { chain ->
            val original = chain.request()
            val newUrl = original
                .url
                .newBuilder()
                .addQueryParameter("access_key", "7d0637578f254e6550858745bc5a14ae")
                .build()
            val request = original
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(request)
        }
    }

    bind<OkHttpClient>() with provider {
        OkHttpClient.Builder()
            .addInterceptor(instance<Interceptor>(tag = "accessKey"))
            .build()
    }

    bind<Retrofit>() with provider {
        Retrofit.Builder()
            .baseUrl("http://api.currencylayer.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(instance())
            .build()
    }

    bind<CurrencyService>() with provider {
        instance<Retrofit>().create(CurrencyService::class.java)
    }

    bind<Api>() with provider { CurrencyApi(instance()) }

}
