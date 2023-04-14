package com.example.museumart_inner.domain.repository

import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.database.EntityArtChicago
import com.example.api_model.pojo.retrofit.ErrorChicago
import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.api_model.pojo.retrofit.OneArtWorkResponse
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.core_api.LoggerApp
import com.example.museumart_inner.CreateCMArtWorkResponse
import com.example.museumart_inner.CreateOneArtWorkResponse
import com.example.museumart_inner.domain.paging.CreatorPaging
import com.example.museumart_inner.domain.paging.CreatorPagingImplTest
import com.example.network.base.*
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.SuccessRepoState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class RepositoryChicagoArtImplTest {

    val dummyCoroutineDispatcher = Dispatchers.Unconfined
    @get: Rule
    val rule = MockKRule(this)
    @RelaxedMockK
    lateinit var retrofitApi: ChicagoMuseumRetrofit
    @RelaxedMockK
    lateinit var dataBaseFavorite: DaoFavoriteArtChicago
    @RelaxedMockK
    lateinit var mapperDataBase: MapperDataBase
    @RelaxedMockK
    lateinit var mapperPojoRetrofit: MapperPojoChicagoRetrofit
    @RelaxedMockK
    lateinit var creatorPaging: CreatorPaging
    @RelaxedMockK
    lateinit var creatorFields: CreatorFields
    @RelaxedMockK
    lateinit var loggerApp: LoggerApp

    private var museumArtWorkResponseFirstPage: MuseumArtWorkResponse? = null
    private var pagingCMArtWorkForResponseFirstPage: PagingCMArtWork? = null
    lateinit var repositoryChicagoArtImpl: RepositoryChicagoArtImpl
    @Before
    fun init() {
        museumArtWorkResponseFirstPage = CreateCMArtWorkResponse.getCMArtWorkResponse(
            currentPage = CreatorPagingImplTest.FIRST_PAGE,
            totalPages = CreatorPagingImplTest.MAX_PAGE
        )
        pagingCMArtWorkForResponseFirstPage = PagingCMArtWork(
            totalPages = CreatorPagingImplTest.MAX_PAGE,
            currentPage = CreatorPagingImplTest.FIRST_PAGE,
            beforePage = null,
            nextPage = CreatorPagingImplTest.FIRST_PAGE + 1
        )
        repositoryChicagoArtImpl = RepositoryChicagoArtImpl(
            retrofitApi = retrofitApi,
            dataBaseFavorite = dataBaseFavorite,
            mapperDataBase = mapperDataBase,
            mapperPojoRetrofit = mapperPojoRetrofit,
            creatorPaging = creatorPaging,
            dispatcher = dummyCoroutineDispatcher,
            creatorFields = creatorFields,
            loggerApp = loggerApp
        )
    }
    @Test
    fun `loadArtWorks get successResultFromRetrofit`() = runBlocking {
        //Arrange
        val checkedState = SuccessRepoState(pagingCMArtWorkForResponseFirstPage)
        coEvery {
            retrofitApi.getArtWorkCM(any(), any())
        } returns Success<MuseumArtWorkResponse>(body = museumArtWorkResponseFirstPage!!)
        coEvery {
            creatorPaging.createPaging(any())
        } returns pagingCMArtWorkForResponseFirstPage!!
        //Act
        val result = repositoryChicagoArtImpl.loadArtWorks(PagingCMArtWork())
        //Access
        assertEquals(checkedState, result)
        print("Result = $result\n")
        print("CheckedValue = $checkedState\n")
    }
    @Test
    fun `loadArtWorks get SuccessEmptyResultFromRetrofit`() = runBlocking {
        //Arrange
        val checkedState = EmptyRepoState
        coEvery {
            retrofitApi.getArtWorkCM(any(), any())
        } returns SuccessEmpty
        //Act
        val result = repositoryChicagoArtImpl.loadArtWorks(PagingCMArtWork())
        //Access
        assertEquals(checkedState, result)
        print("Result = $result\n")
        print("CheckedValue = $checkedState\n")
    }
    @Test
    fun `loadArtWorks get ApiErrorResultFromRetrofit ErrorCodeLessThen400`() = runBlocking {
        //Arrange
        val checkedState = ErrorLoadRepoState
        val error = ErrorChicago(
            status = 300,
            error = "Error msg",
            detail = "any details"
        )
        coEvery {
            retrofitApi.getArtWorkCM(any(), any())
        } returns ApiError<ErrorChicago>(body = error, code = 300)
        //Act
        val result = repositoryChicagoArtImpl.loadArtWorks(PagingCMArtWork())
        //Access
        assertEquals(checkedState, result)
        print("Result = $result\n")
        print("CheckedValue = $checkedState\n")
    }

    @Test
    fun `loadArtWorks get ApiErrorResultFromRetrofit ErrorApiError`() = runBlocking {
        //Arrange
        val error = ErrorChicago(
            status = 404,
            error = "Error msg",
            detail = "any details"
        )
        val checkedState = ErrorLoadRepoState
        coEvery {
            retrofitApi.getArtWorkCM(any(), any())
        } returns ApiError(body = error, code = 404)
        every { loggerApp.logError(any()) } just runs

        //Act
        val result = repositoryChicagoArtImpl.loadArtWorks(PagingCMArtWork())
        print("Result = $result\n")
        //Access
        assertEquals(checkedState, result)
        verify(exactly = 1) {
            loggerApp.logError("RepositoryChicagoArtImpl ApiError body:${error} code: 404")
        }
    }

    @Test
    fun `loadOneArtWork get successResultFromRetrofit`() = runBlocking {
        //Arrange
        val artFromRetrofit = CreateOneArtWorkResponse.getOneArtWorkResponse()
        val artWork = CMArtWork(
            id = artFromRetrofit.data.id,
            title = artFromRetrofit.data.title!!,
            image = artFromRetrofit.data.imageId!!,
            artist = artFromRetrofit.data.artistDisplay!!,
            dataCreate = artFromRetrofit.data.dateEnd.toString(),
            placeCreate = artFromRetrofit.data.placeOfOrigin!!,
            isFavorite = false
        )
        val checkedState = SuccessRepoState(
            CMArtWork(
                id = 157,
                title = "Art about cats",
                dataCreate = "1587",
                artist = "Unknown Artist",
                placeCreate = "Africa",
                image = "",
                isFavorite = false
            )
        )
        coEvery {
            retrofitApi.getOneArtWorkCM(any(), any())
        } returns Success<OneArtWorkResponse>(body = artFromRetrofit)
        coEvery {
            mapperPojoRetrofit.mapOneArtFromNetwork(any())
        } returns artWork
        //Act
        val result = repositoryChicagoArtImpl.loadOneWork(158)
        //Access
        assertEquals(checkedState, result)
        print("Result = $result\n")
        print("CheckedValue = $checkedState\n")
    }
    @Test
    fun `loadOneArtWork get emptySuccessResultFromRetrofit`() = runBlocking {
        //Arrange
        val checkedState = EmptyRepoState
        coEvery {
            retrofitApi.getOneArtWorkCM(any(), any())
        } returns SuccessEmpty
        //Act
        val result = repositoryChicagoArtImpl.loadOneWork(158)
        //Access
        assertEquals(checkedState, result)
        print("Result = $result\n")
        print("CheckedValue = $checkedState\n")
    }
    @Test
    fun `loadOneArtWork get anotherResultsFromRetrofit`() = runBlocking {
        //Arrange
        val checkedState = ErrorLoadRepoState
        coEvery {
            retrofitApi.getOneArtWorkCM(any(), any())
        } returns ApiError(ErrorChicago(300, "Error", "Any details"), 300)
        //Act
        val resultApiError = repositoryChicagoArtImpl.loadOneWork(158)
        //Access
        assertEquals(checkedState, resultApiError)
        print("Result = $resultApiError\n")
        print("CheckedValue = $checkedState\n")
        coEvery {
            retrofitApi.getOneArtWorkCM(any(), any())
        } returns NetworkError(IOException())
        //Act
        val resultNetworkError = repositoryChicagoArtImpl.loadOneWork(158)
        //Access
        assertEquals(checkedState, resultNetworkError)
        print("Result = $resultNetworkError\n")
        print("CheckedValue = $checkedState\n")
        coEvery {
            retrofitApi.getOneArtWorkCM(any(), any())
        } returns UnknownError(IOException())
        //Act
        val resultUnknownError = repositoryChicagoArtImpl.loadOneWork(158)
        //Access
        assertEquals(checkedState, resultUnknownError)
        print("Result = $resultUnknownError\n")
        print("CheckedValue = $checkedState\n")
    }
    @Test
    fun `getOneArtFromDB get successResultFromDataBase`() = runBlocking {
        //Arrange
        val entityArtChicago = EntityArtChicago(
            id = 1,
            idArt = 256,
            title = "Title Art",
            image = "",
            artist = "Van Gog",
            dataCreate = "1872",
            placeCreate = "France"
        )
        val artWorkFromDB = CMArtWork(
            id = 256,
            title = "Title Art",
            image = "",
            artist = "Van Gog",
            dataCreate = "1872",
            placeCreate = "France",
            isFavorite = true
        )
        coEvery { dataBaseFavorite.loadOneArtWork(any()) } returns entityArtChicago
        coEvery { mapperDataBase.mapFromEntityArtChicago(any()) } returns artWorkFromDB
        //Act
        val result = repositoryChicagoArtImpl.getOneArtFromDB(256)
        //Access
        assertEquals(artWorkFromDB, result)
    }
    @Test
    fun `getOneArtFromDB get emptyResultFromDataBase`() = runBlocking {
        //Arrange
        coEvery { dataBaseFavorite.loadOneArtWork(any()) } returns null
        //Act
        val result = repositoryChicagoArtImpl.getOneArtFromDB(256)
        //Access
        assertTrue(result == null)
    }
    @Test
    fun `addToDB verify that call method only one times and right arguments`() = runBlocking {
        //Arrange
        val artWork = CMArtWork(
            id = 256,
            title = "Title Art",
            image = "",
            artist = "Van Gog",
            dataCreate = "1872",
            placeCreate = "France",
            isFavorite = true
        )
        val entityArtChicago = EntityArtChicago(
            id = 1,
            idArt = 256,
            title = "Title Art",
            image = "",
            artist = "Van Gog",
            dataCreate = "1872",
            placeCreate = "France"
        )
        coEvery { dataBaseFavorite.addToFavoriteChicagoArt(any()) } just runs
        coEvery{ mapperDataBase.mapInEntityArtChicago(artWork)} returns entityArtChicago
        //Act
        repositoryChicagoArtImpl.addToDB(artWork)
        //Access
        coVerify(exactly = 1){
            dataBaseFavorite.addToFavoriteChicagoArt(entityArtChicago)
        }
    }
    @Test
    fun `addToDB verify that call method return False after got Exception`() = runBlocking {
        //Arrange
        val artWork = CMArtWork(
            id = 256,
            title = "Title Art",
            image = "",
            artist = "Van Gog",
            dataCreate = "1872",
            placeCreate = "France",
            isFavorite = false
        )

        coEvery {
            dataBaseFavorite.addToFavoriteChicagoArt(any())
        } throws Exception ("Exception")
        //Act

         val result = repositoryChicagoArtImpl.addToDB(artWork)

        //Access
        assertFalse(result)
    }
    @Test
    fun `deleteFromDB verify that call method only one times and right arguments`() = runBlocking {
        //Arrange
        val id = 548
        coEvery {
            dataBaseFavorite.deleteChicagoArtFromFavorite(any())
        } just runs
        //Act
        repositoryChicagoArtImpl.deleteFromDB(548)
        //Access
        coVerify(exactly = 1){
            dataBaseFavorite.deleteChicagoArtFromFavorite(id)
        }
    }
    @Test
    fun `deleteFromDB verify that call method return false`() = runBlocking {
        //Arrange
        coEvery {
            dataBaseFavorite.deleteChicagoArtFromFavorite(any())
        } throws Exception()
        //Act
         val result = repositoryChicagoArtImpl.deleteFromDB(548)
        //Access
        assertFalse(result)
    }
    @Test
    fun `deleteFromDB verify that call method return true`() = runBlocking {
        //Arrange
        coEvery {
            dataBaseFavorite.deleteChicagoArtFromFavorite(any())
        } just runs
        //Act
        val result = repositoryChicagoArtImpl.deleteFromDB(548)
        //Access
        assertTrue(result)
    }
}
