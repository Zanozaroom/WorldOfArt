package com.example.core_inner.streams

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.example.core_api.ImageOutputStream
import com.example.core_api.exception.PhoneSavedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject

class ImageOutputStreamImpl @Inject constructor(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher
) : ImageOutputStream {
    override suspend fun saveImage(uriString: String, bitmap: Bitmap) {
        withContext(dispatcher) {
            try {
                context.contentResolver.openOutputStream(Uri.parse(uriString))?.use { stream ->
                    val bytes = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, SAVED_QUALITY_IMAGE, bytes)
                    stream.write(bytes.toByteArray())
                }
                true
            } catch (e: IOException) {
                throw PhoneSavedException(e.stackTraceToString())
            }
        }
    }
    companion object {
        private const val SAVED_QUALITY_IMAGE = 100
    }
}
