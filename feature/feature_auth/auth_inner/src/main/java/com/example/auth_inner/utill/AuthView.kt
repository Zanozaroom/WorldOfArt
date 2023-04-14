package com.example.auth_inner.utill

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.auth_inner.R
import com.example.auth_inner.utill.AuthView.Companion.ActionAuthView
import com.example.core_api.utills.hideKeyWithoutAction
import com.example.core_api.utills.textChanges



typealias ActionAuthViewListener = (ActionAuthView) -> Unit

class AuthView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    constructor(context: Context) : this(context, null)

    private var listenerAction: ActionAuthViewListener? = null

    private var isHaveAccount: Button? = null
    private var actionMainButton: Button? = null
    private var textEmail: EditText? = null
    private var textPassword: EditText? = null
    private var textHeader: TextView? = null
    private var progressBar: ProgressBar? = null
    private var textProgress: TextView? = null
    private var imageView: ImageView? = null

    init {
        inflate(context, R.layout.fragment_auth, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        isHaveAccount = findViewById(R.id.btnIsHaveAccount)
        actionMainButton = findViewById(R.id.btnActionGo)
        textEmail = findViewById(R.id.textEmail)
        textPassword = findViewById(R.id.textPassword)
        textHeader = findViewById(R.id.headerText)
        progressBar = findViewById(R.id.progressBar)
        textProgress = findViewById(R.id.progressText)
        imageView = findViewById(R.id.imageView)
        initializerListener()
    }

    fun setIsHaveAccount(text: String) {
        isHaveAccount?.text = text
    }
    fun setActionMainButton(text: String) {
        actionMainButton?.text = text
    }
    fun setTextHeader(text: String) {
        textHeader?.text = text
    }
    fun setTextProgress(text: String) {
        textProgress?.text = text
    }

    fun getEmailText() = textEmail!!.text.toString().trim()
    fun getPasswordText() = textPassword!!.text.toString().trim()

    fun setListenerActionsPageArt(listener: ActionAuthViewListener?) {
        this.listenerAction = listener
    }

    fun setViewLoginState(viewModelState: ViewLoginState) {
        when (viewModelState) {
            TryDoItState -> updateState(true)
            WaitUserDataState -> updateState(false)
        }
    }
    fun setResultTextChange(result: EditTextResult) {
        actionMainButton!!.isEnabled = false
        when (result) {
            EditTextResult.ERR_PASSWORD_FIELD -> {
                actionMainButton!!.isEnabled
                textPassword!!.error = resources.getString(R.string.textNotCorrectPassword)
            }
            EditTextResult.ERR_EMAIL_FIELD -> {
                actionMainButton!!.isEnabled
                textEmail!!.error = resources.getString(R.string.textNotCorrectEmail)
            }
            EditTextResult.ALL_RIGHT -> {
                actionMainButton!!.isEnabled = true
            }
            EditTextResult.EMPTY_FIELD -> {
                actionMainButton!!.isEnabled = false
            }
        }
    }

    fun getFlowPassword() = textPassword!!.textChanges()
    fun getFlowEmail() = textEmail!!.textChanges()

    private fun initializerListener() {
        actionMainButton?.setOnClickListener {
            textEmail!!.hideKeyWithoutAction()
            textPassword!!.hideKeyWithoutAction()
            this.listenerAction?.invoke(ActionAuthView.ACTION_START)
        }
        isHaveAccount?.setOnClickListener {
            this.listenerAction?.invoke(ActionAuthView.IS_HAVE_ACCOUNT)
        }
    }

    private fun updateState(isTryLogin: Boolean = false) {
        actionMainButton!!.isEnabled = !isTryLogin
        progressBar!!.isVisible = isTryLogin
        textProgress!!.isVisible = isTryLogin
        isHaveAccount!!.isEnabled = !isTryLogin
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        isHaveAccount = null
        actionMainButton = null
        textEmail = null
        textPassword = null
        textHeader = null
        progressBar = null
        textProgress = null
        imageView = null
        listenerAction = null
    }
    companion object {
        enum class EditTextResult {
            ERR_PASSWORD_FIELD, ERR_EMAIL_FIELD, ALL_RIGHT, EMPTY_FIELD
        }
        enum class ActionAuthView {
            ACTION_START, IS_HAVE_ACCOUNT
        }
    }
}