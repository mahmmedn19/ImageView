package com.example.imageview.ui.feature.profile

import androidx.lifecycle.viewModelScope
import com.example.imageview.domain.usecase.GetProfileInfoUseCase
import com.example.imageview.ui.base.BaseViewModel
import com.example.imageview.ui.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileInfoUseCase: GetProfileInfoUseCase,
) : BaseViewModel<ProfileUiState, Int>(ProfileUiState()), ProfileInteractionListener {
    override val TAG: String = this::class.java.simpleName

    init {
        getUserInfo()
        getAlbums()
    }

    private fun getUserInfo() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getProfileInfoUseCase.getUserInfo()!!.toUserInfoUiState() },
            ::onGeUserInfoSuccess,
            ::onGetUserInfoError,
        )
    }

    private fun onGeUserInfoSuccess(userInfo: UserInfoUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                user = userInfo
            )
        }
        log("data fetched successfully: ${state.value.user}")
    }

    private fun onGetUserInfoError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, isError = true) }
        log("error fetching data")
    }


    private fun getAlbums() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getProfileInfoUseCase.getAlbums().map { it.toAlbumsUiState() } },
            ::onGetAlbumsSuccess ,
            ::onGetUserAlbumsError
        )
    }

    private fun onGetAlbumsSuccess(userAlbums: List<AlbumsUiState>) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                userAlbums = userAlbums
            )
        }
    }

    private fun onGetUserAlbumsError(throwable: Throwable) =
        _state.update { it.copy(isLoading = false, isError = true) }

    override fun onClickAlbum(albumId: Int) {
        viewModelScope.launch { _effect.emit(EventHandler(albumId)) }
    }
}