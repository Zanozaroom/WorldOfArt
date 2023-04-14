package com.example.museumart_inner.domain.saving

import android.graphics.Bitmap
import android.util.Log
import com.example.core_api.ImageInputStream
import com.example.core_api.ImageOutputStream
import com.example.core_api.LoggerApp
import com.example.core_api.exception.NetworkUrlException
import com.example.core_api.exception.PhoneSavedException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.mockkStatic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test


class SaverImplTest {
    @get: Rule
    val rule = MockKRule(this)

    @RelaxedMockK
    private lateinit var inputImageStreamer: ImageInputStream

    @RelaxedMockK
    private lateinit var outputImageStreamer: ImageOutputStream
    @RelaxedMockK
    private lateinit var bitmap: Bitmap
    @RelaxedMockK
    private lateinit var loggerApp: LoggerApp

    private val dummyCoroutineDispatcher = Dispatchers.Unconfined
    private var saverImpl: SaverImpl? = null

    @Before
    fun init() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        saverImpl = SaverImpl(
            inputImageStreamer,
            outputImageStreamer,
            dummyCoroutineDispatcher,
            loggerApp
        )
    }

    @After
    fun closed() {
        saverImpl = null
    }

    @Test
    fun `saveInPhone checked that was success result loading and saved image`() = runBlocking {
        //Arrange
        coEvery {
            inputImageStreamer.loadStream(any())
        } returns bitmap
        //Act
        val result = saverImpl!!.saveInPhone("url", "uriString")
        //Access
        assertTrue(result)
    }

    @Test
    fun `saveInPhone checked that was gotten null result loading and not saved image`() = runBlocking {
        //Arrange
        coEvery {
            inputImageStreamer.loadStream(any())
        } returns null
        //Act
        val result = saverImpl!!.saveInPhone("url", "uriString")
        //Access
        assertFalse(result)
    }

    @Test
    fun `saveInPhone checked that was gotten NetworkUrlException end return Boolean = false`() = runBlocking {
        //Arrange
        coEvery {
            inputImageStreamer.loadStream(any())
        } throws  NetworkUrlException("NetworkUrlException inputImageStreamer")
        //Act
        val result = saverImpl!!.saveInPhone("url", "uriString")
        //Access
        assertFalse(result)
    }

    @Test
    fun `saveInPhone checked that was gotten PhoneSavedException end return Boolean = false`() = runBlocking {
        //Arrange
        coEvery {
            inputImageStreamer.loadStream(any())
        } throws  PhoneSavedException("NetworkUrlException inputImageStreamer")
        //Act
        val result = saverImpl!!.saveInPhone("url", "uriString")
        //Access
        assertFalse(result)
    }
}
