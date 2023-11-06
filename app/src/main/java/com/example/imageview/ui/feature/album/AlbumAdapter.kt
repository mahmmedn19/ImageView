package com.example.imageview.ui.feature.album

import com.example.imageview.R
import com.example.imageview.ui.base.BaseAdapter
import com.example.imageview.ui.base.BaseInteractionListener


class AlbumAdapter(listener: AlbumInteractionListener)
    : BaseAdapter<AlbumPhotos>(listener) {
    override val layoutId: Int = R.layout.item_album_photo
}
interface AlbumInteractionListener: BaseInteractionListener {
    fun onClickAlbumImage(imageId: Int)
}