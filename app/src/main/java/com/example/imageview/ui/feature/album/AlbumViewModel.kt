package com.example.imageview.ui.feature.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.imageview.domain.usecase.GetAllPhotosByAlbumIdUseCase
import com.example.imageview.ui.base.BaseViewModel
import com.example.imageview.ui.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAllPhotosByAlbumIdUseCase: GetAllPhotosByAlbumIdUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AlbumUiState, Int>(AlbumUiState()), AlbumInteractionListener {
    override val TAG: String = this::class.java.simpleName
    private val queryFlow = MutableStateFlow("")
    private val args = AlbumFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        getAllPhotosForAlbum()
        viewModelScope.launch {
            queryFlow
                .debounce(200L)
                .filter { it == queryFlow.value }  // Ensure the query hasn't changed in the meantime
                .collectLatest { query ->
                    handleSearchQuery(query)
                }
        }
    }

    private fun getAllPhotosForAlbum() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getAllPhotosByAlbumIdUseCase(args.id).map { it.toAlbumPhotos() } },
            ::onGetUserAlbumPhotosSuccess,
            ::onGetUserAlbumPhotosError
        )
    }

    private fun onGetUserAlbumPhotosSuccess(albumPhotos: List<AlbumPhotos>) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                albumPhotos = albumPhotos,
                searchedPhoto = albumPhotos
            )
        }
    }

    private fun onGetUserAlbumPhotosError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, isError = true) }
    }

    private fun handleSearchQuery(query: String) {
        val albumPhotos = _state.value.albumPhotos

        val searchedPhotos = if (query.isEmpty()) {
            albumPhotos
        } else {
            albumPhotos.filter { it.title?.contains(query, true) == true }
        }

        _state.update { it.copy(searchedPhoto = searchedPhotos) }
    }


    fun searchPhotoByTitle(query: String) {
        queryFlow.value = query
    }

    override fun onClickAlbumImage(imageId: Int) {
        viewModelScope.launch { _effect.emit(EventHandler(imageId)) }
    }

}