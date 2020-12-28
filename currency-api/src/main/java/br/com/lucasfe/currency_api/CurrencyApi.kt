package br.com.lucasfe.currency_api

import br.com.lucasfe.currency_api.model.ConversionRate
import br.com.lucasfe.currency_api.model.Currency
import io.reactivex.Single

class CurrencyApi(private val service: CurrencyService) : Api {

    override fun getAvailableCurrencies(): Single<List<Currency>> =
        Single
            .create<List<Currency>> { emitter ->
                try {
                    val currencies = service
                        .requestAvailableCurrencies()
                        .map { response ->
                            if (response.success) {
                                response.currencies.map { Currency(it.key, it.value) }
                            } else {
                                throw Exception("Service request failed")
                            }
                        }
                        .blockingGet()

                    emitter.onSuccess(currencies)
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
    override fun getConversionRates(): Single<List<ConversionRate>> =
        Single
            .create<List<ConversionRate>> { emitter ->
                try {
                    val rates = service
                        .requestConversionRates()
                        .map { response ->
                            if (response.success) {
                                response.quotes.map {
                                    ConversionRate(
                                        it.key.substring(0, 3),
                                        it.key.substring(3),
                                        it.value
                                    )
                                }
                            } else {
                                throw Exception("Service request failed")
                            }
                        }
                        .blockingGet()

                    emitter.onSuccess(rates)
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
}