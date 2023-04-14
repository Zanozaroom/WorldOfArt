package com.example.auth_inner.utill

sealed class FlowEditTextResult{
    object NullData : FlowEditTextResult()
    object NotCorrectPassword : FlowEditTextResult()
    object NotCorrectLogin : FlowEditTextResult()
    object AllCorrect : FlowEditTextResult()
}

