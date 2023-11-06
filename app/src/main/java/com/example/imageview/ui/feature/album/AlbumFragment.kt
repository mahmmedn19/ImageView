package com.example.imageview.ui.feature.album

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imageview.R
import com.example.imageview.databinding.FragmentAlbumBinding
import com.example.imageview.ui.base.BaseFragment
import com.example.imageview.ui.utils.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_album
    override val viewModel: AlbumViewModel by viewModels()
    private val albumAdapter: AlbumAdapter by lazy { AlbumAdapter(viewModel) }
    override fun setup() {
        initiateAdapter()
        collectAction()
    }

    private fun initiateAdapter() {
        binding.rvAlbumPhotos.adapter = albumAdapter
    }

    private fun collectAction() {
        collect(viewModel.effect) { effect ->
            effect.getContentIfNotHandled()?.let { navigateToViewAlbumImageFragment(it) }
        }
    }

    private fun navigateToViewAlbumImageFragment(imageId: Int) {
        val action = AlbumFragmentDirections
            .actionAlbumFragmentToPhotoFragment(imageId)
        findNavController().navigate(action)
    }
}