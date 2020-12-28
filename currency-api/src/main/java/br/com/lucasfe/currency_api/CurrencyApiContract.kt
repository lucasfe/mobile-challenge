package br.com.lucasfe.currency_api

import br.com.lucasfe.currency_api.model.ConversionRate
import br.com.lucasfe.currency_api.model.Currency
import io.reactivex.Single

interface Api {
    fun getAvailableCurrencies(): Single<List<Currency>>
    fun getConversionRates(): Single<List<ConversionRate>>
}
