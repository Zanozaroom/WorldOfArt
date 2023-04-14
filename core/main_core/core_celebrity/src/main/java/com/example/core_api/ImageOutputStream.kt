package com.example.core_api

import android.graphics.Bitmap

interface ImageOutputStream {
    /**
     * Saved Image on Phone or throw PhoneSavedException
     */
    suspend fun saveImage(uriString: String, bitmap: Bitmap)
}
