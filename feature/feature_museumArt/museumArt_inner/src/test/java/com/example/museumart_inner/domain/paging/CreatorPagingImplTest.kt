package com.example.museumart_inner.domain.paging

import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.museumart_inner.CreateCMArtWorkResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
class CreatorPagingImplTest {
    companion object {
        const val FIRST_PAGE = 1
        const val MAX_PAGE = 100
        const val MEDIUM_PAGE = 50
    }
    private var museumArtWorkResponseFirstPage: MuseumArtWorkResponse? = null
    private var museumArtWorkResponseLatestPage: MuseumArtWorkResponse? = null
    private var museumArtWorkResponseMediumPage: MuseumArtWorkResponse? = null
    private var pagingCMArtWorkForResponseFirstPage: PagingCMArtWork? = null
    private var pagingCMArtWorkForResponseLatestPage: PagingCMArtWork? = null
    private var pagingCMArtWorkForResponseMediumPage: PagingCMArtWork? = null
    private var creatorPagingImpl: CreatorPagingImpl? = null
    @Before
    fun createResponse() {
        creatorPagingImpl = CreatorPagingImpl()
        museumArtWorkResponseFirstPage = CreateCMArtWorkResponse.getCMArtWorkResponse(
            currentPage = FIRST_PAGE,
            totalPages = MAX_PAGE
        )
        pagingCMArtWorkForResponseFirstPage = PagingCMArtWork(
            totalPages = MAX_PAGE,
            currentPage = FIRST_PAGE,
            beforePage = null,
            nextPage = FIRST_PAGE+1
        )
        museumArtWorkResponseLatestPage = CreateCMArtWorkResponse.getCMArtWorkResponse(
            currentPage = MAX_PAGE,
            totalPages = MAX_PAGE
        )
        pagingCMArtWorkForResponseLatestPage = PagingCMArtWork(
            totalPages = MAX_PAGE,
            currentPage = MAX_PAGE,
            beforePage = MAX_PAGE-1,
            nextPage = null
        )
        museumArtWorkResponseMediumPage = CreateCMArtWorkResponse.getCMArtWorkResponse(
            currentPage = MEDIUM_PAGE,
            totalPages = MAX_PAGE
        )
        pagingCMArtWorkForResponseMediumPage = PagingCMArtWork(
            totalPages = MAX_PAGE,
            currentPage = MEDIUM_PAGE,
            beforePage = MEDIUM_PAGE-1,
            nextPage = MEDIUM_PAGE+1
        )
    }
    @After
    fun cleared(){
        museumArtWorkResponseFirstPage = null
        museumArtWorkResponseLatestPage = null
        museumArtWorkResponseMediumPage = null
        creatorPagingImpl = null
    }
    @Test
    fun `test createPaging GetFirstPage AssertRightPre-Result ReturnEqualsData`() {
        //Arrange
        museumArtWorkResponseFirstPage
        pagingCMArtWorkForResponseFirstPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseFirstPage!!)
        //Assert
        Assert.assertEquals(pagingCMArtWorkForResponseFirstPage, result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseFirstPage\n")
    }
    @Test
    fun `test createPaging GetFirstPage AssertWrongPre-Result ReturnNotEqualsData`() {
        //Arrange
        museumArtWorkResponseFirstPage
        pagingCMArtWorkForResponseMediumPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseFirstPage!!)
        //Assert
        Assert.assertFalse(pagingCMArtWorkForResponseMediumPage == result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseMediumPage\n")
    }
    @Test
    fun `test createPaging GetMediumPage AssertRightPre-Result ReturnEqualsData`() {
        //Arrange
        museumArtWorkResponseMediumPage
        pagingCMArtWorkForResponseMediumPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseMediumPage!!)
        //Assert
        Assert.assertEquals(pagingCMArtWorkForResponseMediumPage, result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseMediumPage\n")
    }
    @Test
    fun `test createPaging GetMediumPage AssertWrongPre-Result ReturnNotEqualsData`() {
        //Arrange
        museumArtWorkResponseMediumPage
        pagingCMArtWorkForResponseLatestPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseMediumPage!!)
        //Assert
        Assert.assertFalse(pagingCMArtWorkForResponseLatestPage == result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseLatestPage\n")
    }
    @Test
    fun `test createPaging GetLatestPage AssertRightPre-Result ReturnEqualsData`() {
        //Arrange
        museumArtWorkResponseLatestPage
        pagingCMArtWorkForResponseLatestPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseLatestPage!!)
        //Assert
        Assert.assertEquals(pagingCMArtWorkForResponseLatestPage, result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseLatestPage\n")
    }
    @Test
    fun `test createPaging GetLatestPage AssertWrongPre-Result ReturnNotEqualsData`() {
        //Arrange
        museumArtWorkResponseLatestPage
        pagingCMArtWorkForResponseMediumPage
        //Act
        val result = creatorPagingImpl!!.createPaging(museumArtWorkResponseLatestPage!!)
        //Assert
        Assert.assertFalse(pagingCMArtWorkForResponseMediumPage == result)
        print("Result = $result\n")
        print("CheckedValue = $pagingCMArtWorkForResponseMediumPage\n")
    }
}
