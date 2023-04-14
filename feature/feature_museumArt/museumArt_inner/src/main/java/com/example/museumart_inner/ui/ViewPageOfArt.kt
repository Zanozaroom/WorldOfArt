package com.example.museumart_inner.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.state.UIState
import com.example.museumart_inner.R

enum class ActionPageOfArt {
    DOWN_LOAD, IS_FAVORITE
}
typealias ActionPageOfArtListener = (ActionPageOfArt) -> Unit
typealias ErrorButtonArtListener = (View) -> Unit

class ViewPageOfArt(
    context: Context,
    attrs: AttributeSet?,
) : ConstraintLayout(context, attrs) {
    constructor(context: Context) : this(context, null)

    private var listenerAction: ActionPageOfArtListener? = null
    private var listenerError: ErrorButtonArtListener? = null

    private var imageArt: ImageView? = null
    private var iconIsInFavorite: ImageButton? = null
    private var iconSaveInPhone: ImageButton? = null
    private var textTitleArtWork: TextView? = null
    private var textAuthorArtWorkHeader: TextView? = null
    private var textAuthorArtWork: TextView? = null
    private var textDataCreatedArtWorkHeader: TextView? = null
    private var textDataCreatedArtWork: TextView? = null
    private var textPlaceCreatedArtWorkHeader: TextView? = null
    private var textPlaceCreatedArtWork: TextView? = null
    private var emptyImage: ImageView? = null
    private var buttonError: Button? = null
    private var textError: TextView? = null
    private var progressBar: ProgressBar? = null


    init {
        inflate(context, R.layout.view_page_of_art, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        imageArt = findViewById(R.id.imageArt)
        iconIsInFavorite = findViewById(R.id.icFavorite)
        iconSaveInPhone = findViewById(R.id.icSaveInPhone)
        textTitleArtWork = findViewById(R.id.titleArtWork)

        textAuthorArtWorkHeader = findViewById(R.id.authorHeader)
        textAuthorArtWork = findViewById(R.id.author_art)

        textDataCreatedArtWorkHeader = findViewById(R.id.createAtDataHeader)
        textDataCreatedArtWork = findViewById(R.id.createAtData)

        textPlaceCreatedArtWork = findViewById(R.id.createAtPlace)
        textPlaceCreatedArtWorkHeader = findViewById(R.id.createAtPlaceHeader)

        emptyImage = findViewById(R.id.emptyImage)
        buttonError = findViewById(R.id.buttonErrorLoad)
        textError = findViewById(R.id.messageErrorLoad)
        progressBar = findViewById(R.id.progressBar)
        initializerListener()
    }

    fun initState(state: UIState<CMArtWork>){
        when(state){
            UIState.Empty -> setState(isEmpty = true)
            UIState.Error -> setState(isError = true)
            UIState.Loading -> setState(isLoading = true)
            is UIState.Success -> {
                setSuccessData(state.data)
                setState(isSuccess = true)
            }
            else -> setState(isEmpty = true)
        }
    }
    fun setListenerActionsPageArt(listener: ActionPageOfArtListener?) {
        this.listenerAction = listener
    }
    fun setListenerErrorPageArt(listener: ErrorButtonArtListener){
        this.listenerError = listener
    }

    private fun setSuccessData(art: CMArtWork) {
        Glide.with(imageArt!!)
            .load(art.image)
            .fitCenter()
            .error(ResourcesCompat.getDrawable(resources, com.example.api_model.R.drawable.not_image, null))
            .into(imageArt!!)

        textTitleArtWork!!.text = art.title
        textAuthorArtWork!!.text = art.artist
        textDataCreatedArtWork!!.text = art.dataCreate
        textPlaceCreatedArtWork!!.text = art.placeCreate

        when(art.isFavorite){
            true -> iconIsInFavorite!!.setImageResource(com.example.commonui.R.drawable.ic_in_favorite)
            false -> iconIsInFavorite!!.setImageResource(com.example.commonui.R.drawable.ic_not_favorite)
        }
    }

    private fun setState(
        isLoading: Boolean = false,
        isSuccess: Boolean = false,
        isError: Boolean = false,
        isEmpty: Boolean = false
    ){
        imageArt?.isVisible = isSuccess
        iconIsInFavorite?.isVisible = isSuccess
        iconSaveInPhone?.isVisible = isSuccess
        textTitleArtWork?.isVisible = isSuccess
        textAuthorArtWork?.isVisible = isSuccess
        textAuthorArtWorkHeader?.isVisible = isSuccess
        textDataCreatedArtWork?.isVisible = isSuccess
        textDataCreatedArtWorkHeader?.isVisible = isSuccess
        textPlaceCreatedArtWork?.isVisible = isSuccess
        textPlaceCreatedArtWorkHeader!!.isVisible = isSuccess
        emptyImage?.isVisible = isEmpty
        buttonError?.isVisible = isError
        textError?.isVisible = isError
        progressBar?.isVisible = isLoading
    }

    private fun initializerListener() {
        iconIsInFavorite?.setOnClickListener {
            this.listenerAction?.invoke(ActionPageOfArt.IS_FAVORITE)
        }
        iconSaveInPhone?.setOnClickListener {
            this.listenerAction?.invoke(ActionPageOfArt.DOWN_LOAD)
        }
        buttonError?.setOnClickListener {
            this.listenerError?.invoke(it)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.listenerAction = null
        this.listenerError = null
        imageArt = null
        iconIsInFavorite = null
        iconSaveInPhone = null
        textTitleArtWork = null
        textAuthorArtWorkHeader = null
        textAuthorArtWork = null
        textDataCreatedArtWorkHeader = null
        textDataCreatedArtWork = null
        textPlaceCreatedArtWorkHeader = null
        textPlaceCreatedArtWork = null
        emptyImage = null
        buttonError = null
        textError = null
        progressBar = null
    }
}