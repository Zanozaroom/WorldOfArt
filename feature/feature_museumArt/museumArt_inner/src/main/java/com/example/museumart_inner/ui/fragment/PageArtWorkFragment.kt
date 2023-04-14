package com.example.museumart_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.museumart_inner.R
import com.example.museumart_inner.ui.ActionPageOfArt
import com.example.museumart_inner.ui.ViewPageOfArt
import com.example.museumart_inner.ui.viewmodel.MuseumBaseViewModel
import com.example.museumart_inner.ui.viewmodel.PageArtWorkViewModel
import kotlinx.coroutines.flow.collectLatest

class PageArtWorkFragment : MuseumBaseFragment(R.layout.fragment_page_art) {
    private val args: PageArtWorkFragmentArgs by navArgs()
    override var toolbar: Toolbar? = null
    lateinit var viewModelPage: PageArtWorkViewModel
    override var viewModelBase: MuseumBaseViewModel? = null

    private val contractSave = registerForActivityResult(
        ActivityResultContracts.CreateDocument(PageArtWorkViewModel.MIME_TYPE)
    ) {
        it?.let { saveImage(it.toString()) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.topAppBar)
        toolbar?.setupWithNavController(findNavController(), appBarConfiguration!!)
        val viewPageArt = view.findViewById<ViewPageOfArt>(R.id.viewOfPage)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelPage.artWork.collectLatest {
                viewPageArt.initState(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelPage.flowSaveData.collectLatest {
                contractSave.launch(it)
            }
        }

        viewPageArt.setListenerActionsPageArt {
            viewModelPage.onClickIconButtonView(it)
        }
        viewPageArt.setListenerErrorPageArt {
            viewModelPage.loadArtWork(args.artWorkId.toInt())
        }
    }

    private fun saveImage(uri: String) {
        viewModelPage.saveImage(uri)
    }

    private fun initViewModel() {
        viewModelPage =
            ViewModelProvider(this, viewModelFactory)[PageArtWorkViewModel::class.java]
        viewModelBase = viewModelPage
        viewModelPage.loadArtWork(args.artWorkId.toInt())
    }
}

