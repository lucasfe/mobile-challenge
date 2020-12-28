package br.com.lucasfe.currency_api

import br.com.lucasfe.currency_api.model.ConversionRateListResponse
import br.com.lucasfe.currency_api.model.CurrencyListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyService {

    @GET("/list")
    fun requestAvailableCurrencies(): Single<CurrencyListResponse>

    @GET("/live")
    fun requestConversionRates(): Single<ConversionRateListResponse>

}