package com.example.favorite_inner.utill

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.api_model.ui.BaseCallBackSwipeMove
import com.example.api_model.ui.SwipeMoveCallback

class SavedArtSwipeIn (context: Context, swipeDelete: SwipeMoveCallback) :
    BaseCallBackSwipeMove(context,swipeDelete) {
    override val flagDrag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
    override val flagSwipe = ItemTouchHelper.START
}