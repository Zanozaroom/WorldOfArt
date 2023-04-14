package com.example.museumart_inner.domain.saving

import com.example.core_api.ImageInputStream
import com.example.core_api.ImageOutputStream
import com.example.core_api.LoggerApp
import com.example.core_api.exception.NetworkUrlException
import com.example.core_api.exception.PhoneSavedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaverImpl @Inject constructor(
    private val inputImageStreamer: ImageInputStream,
    private val outputImageStreamer: ImageOutputStream,
    private val dispatcher: CoroutineDispatcher,
    private val loggerApp: LoggerApp
): Saver {
    override suspend fun saveInPhone(
        url: String, uriString: String
    ): Boolean = withContext(dispatcher){
        try {
            val bitmap = inputImageStreamer.loadStream(url)
            if (bitmap != null) {
                outputImageStreamer.saveImage(uriString, bitmap)
                true
            }else false
        } catch (e: NetworkUrlException) {
            loggerApp.logError("Saver e.errorMessage")
            false
        } catch (e: PhoneSavedException) {
            loggerApp.logError("Saver e.errorMessage")
            false
        } catch (e: Exception){
            loggerApp.logError("Saver UnknownExp e.errorMessage")
            false
        }
    }
}