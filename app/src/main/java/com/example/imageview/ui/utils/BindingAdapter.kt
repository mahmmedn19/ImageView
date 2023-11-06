package com.example.imageview.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageview.R
import com.example.imageview.ui.base.BaseAdapter
import com.example.imageview.ui.feature.album.AlbumViewModel

@BindingAdapter("app:showIfTrue")
fun showIfTrue(view: View, condition: Boolean) {
    view.isVisible = condition
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter("app:loadImage")
fun bindImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        Glide.with(image)
            .load(imageURL)
            .placeholder(R.drawable.loading_animation)
            .centerCrop().into(image)
    }
}

@BindingAdapter("app:showPlaceholder")
fun showPlaceholder(imageView: ImageView, show: Boolean) {
    if (show) {
        imageView.visibility = View.VISIBLE
    } else {
        imageView.visibility = View.GONE
    }
}

@BindingAdapter("app:onQueryTextSubmit")
fun setOnQueryTextSubmitListener(searchView: SearchView, viewModel: AlbumViewModel) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            (viewModel as? AlbumViewModel)?.searchPhotoByTitle(query.orEmpty())
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            (viewModel as? AlbumViewModel)?.searchPhotoByTitle(newText.orEmpty())
            return true
        }
    })
}
