package br.com.lucasfe.currency_api.model

import com.google.gson.annotations.Expose

abstract class Response(
    @Expose val success: Boolean,
    @Expose val terms: String,
    @Expose val privacy: String
)

data class Currency(val symbol: String, val name: String)

class CurrencyListResponse(
    success: Boolean, terms: String, privacy: String, @Expose val currencies: Map<String, String>
) : Response(success, terms, privacy)

data class ConversionRate(val fromSymbol: String, val toSymbol: String, val rate: Double)

class ConversionRateListResponse(
    success: Boolean, terms: String, privacy: String, @Expose val timestamp: Long,
    @Expose val source: String, @Expose val quotes: Map<String, Double>
) : Response(success, terms, privacy)


