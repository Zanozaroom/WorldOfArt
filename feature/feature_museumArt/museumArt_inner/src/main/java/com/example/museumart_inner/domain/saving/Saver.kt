package com.example.museumart_inner.domain.saving

interface Saver {
    suspend fun saveInPhone(url: String, uriString: String): Boolean
}