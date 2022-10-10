package com.example.quotesapp

interface QuotesResponseListener {
    fun didFatch(response: List<QuotesResponse>, message: String)
    fun didError(message: String)


}