package com.example.api_model.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.*
import com.example.api_model.R
import com.example.api_model.state.UIState

enum class ButtonsAction {
    ERROR_LOAD, ERROR_LOAD_MORE
}

typealias ButtonsActionListener = (ButtonsAction) -> Unit

interface OnScrollRecycler {
    fun onScroll()
}

class ViewWithRecycler(
    context: Context,
    attrs: AttributeSet?,
) : ConstraintLayout(context, attrs) {
    constructor(context: Context) : this(context, null)

    private var listener: ButtonsActionListener? = null

    private var buttonErrorLoad: Button? = null
    private var buttonErrorLoadMore: Button? = null
    private var imageEmpty: ImageView? = null
    private var textErrorLoad: TextView? = null
    private var processBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    init {
        inflate(context, R.layout.view_stateui, this)
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.ViewWithRecycler)
            imageEmpty?.setImageDrawable(attributes.getDrawable(R.styleable.ViewWithRecycler_imageEmpty))
            textErrorLoad?.text = attributes.getString(R.styleable.ViewWithRecycler_textError)
            attributes.recycle()
        }
        initializerListener()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        imageEmpty = findViewById(R.id.emptyImage)
        textErrorLoad = findViewById(R.id.messageErrorLoad)
        buttonErrorLoad = findViewById(R.id.buttonErrorLoad)
        buttonErrorLoadMore = findViewById(R.id.buttonErrorLoadMore)
        processBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerViewData)
    }

    fun setStateView(state: UIState<*>) {
        when (state) {
            is UIState.Empty -> isState(empty = true)
            is UIState.Error -> isState(error = true)
            is UIState.Loading -> isState(loading = true)
            is UIState.ErrorLoadMore -> isState(errorLoadMore = true, success = true)
            is UIState.Success -> {
                isState(success = true)
            }
        }

    }

    private fun isState(
        success: Boolean = false,
        loading: Boolean = false,
        empty: Boolean = false,
        error: Boolean = false,
        errorLoadMore: Boolean = false
    ) {
        buttonErrorLoad?.isVisible = error
        textErrorLoad?.isVisible = error
        buttonErrorLoadMore?.isVisible = errorLoadMore
        imageEmpty?.isVisible = empty
        processBar?.isVisible = loading
        recyclerView?.isVisible = success
    }

    fun setRecyclerView(
        adapter: RecyclerView.Adapter<*>?,
        layoutManager: RecyclerView.LayoutManager,
        typeLayoutManager: Int,
        itemCallBack: ItemTouchHelper.Callback? = null,
        onScroll: OnScrollRecycler? = null
    ) {
        if (adapter == null) return
        recyclerView?.adapter = adapter
        val getManager = when (typeLayoutManager) {
            LINER_MANAGER -> layoutManager as LinearLayoutManager
            GRID_MANAGER -> layoutManager as GridLayoutManager
            else -> layoutManager as LinearLayoutManager
        }

        recyclerView?.layoutManager = getManager
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val positionNow = getManager.findLastCompletelyVisibleItemPosition()
                if (totalItemCount < positionNow + POSITION_FOR_LOADING) {
                    onScroll?.onScroll()
                }
            }
        })
        if (itemCallBack != null) {
            ItemTouchHelper(itemCallBack).attachToRecyclerView(recyclerView)
        }
    }

    private fun initializerListener() {
        buttonErrorLoad?.setOnClickListener {
            this.listener?.invoke(ButtonsAction.ERROR_LOAD)
        }

    }

    fun setListenerErrorButton(listener: ButtonsActionListener?) {
        this.listener = listener
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.listener = null
        buttonErrorLoad = null
        buttonErrorLoadMore = null
        imageEmpty = null
        textErrorLoad = null
        processBar = null
        recyclerView = null
    }

    companion object {
        const val LINER_MANAGER = 0
        const val GRID_MANAGER = 1
        const val POSITION_FOR_LOADING = 5
    }
}