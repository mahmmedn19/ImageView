package com.example.imageview.ui.feature.profile

import com.example.imageview.R
import com.example.imageview.ui.base.BaseAdapter
import com.example.imageview.ui.base.BaseInteractionListener

class ProfileAdapter(listener: ProfileInteractionListener): BaseAdapter<AlbumsUiState>(listener) {
    override val layoutId: Int = R.layout.item_album
}

interface ProfileInteractionListener: BaseInteractionListener {
    fun onClickAlbum(albumId: Int)
}