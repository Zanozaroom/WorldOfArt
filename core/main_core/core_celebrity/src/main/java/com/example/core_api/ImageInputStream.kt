package com.example.core_api

import android.graphics.Bitmap

interface ImageInputStream {
    /**
     * Return Bitmap image or throw NetworkUrlException
     */
    suspend fun loadStream(url: String): Bitmap?
}
