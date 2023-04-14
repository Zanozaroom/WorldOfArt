package com.example.core_inner.streams

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.core_api.ImageInputStream
import com.example.core_api.exception.NetworkUrlException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class ImageInputStreamImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
    ): ImageInputStream {
    override suspend fun loadStream(url: String): Bitmap {
        return withContext(dispatcher) {
            try {
                val urlGet = URL(url)
              BitmapFactory.decodeStream(urlGet.openConnection().getInputStream())
            } catch (e: IOException) {
                throw NetworkUrlException(e.stackTraceToString())
            }
        }
    }
    }