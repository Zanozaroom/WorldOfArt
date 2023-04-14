package com.example.favorite_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_model.state.UIState
import com.example.api_model.ui.Move
import com.example.api_model.ui.ViewWithRecycler
import com.example.favorite_inner.R
import com.example.favorite_inner.ui.viewmodel.UserFavoriteBaseViewModel
import com.example.favorite_inner.ui.viewmodel.UserFavoriteViewModel
import com.example.favorite_inner.utill.CMFavoriteArtAdapter
import com.example.favorite_inner.utill.SavedArtSwipeIn
import kotlinx.coroutines.launch

class FavoriteArtFragment : FavoriteArtFragmentBase(R.layout.fragment_favorite_art){
    private lateinit var viewModelCMUserArtWork: UserFavoriteViewModel

    private val adapterListArt: CMFavoriteArtAdapter by lazy {
        CMFavoriteArtAdapter(viewModelCMUserArtWork)
    }
    override var toolbar: Toolbar? = null
    override var baseViewModel: UserFavoriteBaseViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.topAppBar)
        toolbar?.setupWithNavController(findNavController(), appBarConfiguration!!)

        val stateViewWithRecycler = view.findViewById<ViewWithRecycler>(R.id.viewWithRecycler)
        initRecycler(stateViewWithRecycler)
        stateViewWithRecycler.setListenerErrorButton { viewModelCMUserArtWork.loadFavorite() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelCMUserArtWork.loadFavorite().collect {
                    if (it is UIState.Success){
                        adapterListArt.submitList(it.data)
                    }
                    stateViewWithRecycler.setStateView(it)
                }
            }
        }
    }


    private fun createViewModel() {
        viewModelCMUserArtWork =
            ViewModelProvider(this, viewModelFactory)[UserFavoriteViewModel::class.java]
        baseViewModel = viewModelCMUserArtWork
    }

    private fun initRecycler(stateViewWithRecycler: ViewWithRecycler){
        stateViewWithRecycler.setRecyclerView(
            adapterListArt,
            LinearLayoutManager(requireContext()),
            typeLayoutManager = ViewWithRecycler.LINER_MANAGER,
            itemCallBack = initCallBack())
    }

    private fun initCallBack(): ItemTouchHelper.Callback {
        val call = SavedArtSwipeIn(requireContext()) { move, startPosition, _ ->
            when (move) {
                Move.START -> {
                    viewModelCMUserArtWork.deleteFromFavoriteList(adapterListArt.currentList[startPosition])
                }
                else -> {}// не используем
            }
        }
        return call
    }

}

