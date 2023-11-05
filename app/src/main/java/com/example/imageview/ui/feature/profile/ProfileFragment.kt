package com.example.imageview.ui.feature.profile


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imageview.R
import com.example.imageview.databinding.FragmentProfileBinding
import com.example.imageview.ui.base.BaseFragment
import com.example.imageview.ui.utils.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()
    private val profileAdapter: ProfileAdapter by lazy { ProfileAdapter(viewModel) }

    override fun setup() {
        initiateAdapter()
        collectAction()
    }

    private fun initiateAdapter() {
        binding.recyclerViewAlbums.adapter = profileAdapter
    }

    private fun collectAction(){
        collect(viewModel.effect) { effect ->
            effect.getContentIfNotHandled()?.let { navigateToAlbumDetailsFragment(it) }
        }
    }

    private fun navigateToAlbumDetailsFragment(id: Int){
        val action = ProfileFragmentDirections.actionProfileFragmentToAlbumFragment(id)
        findNavController().navigate(action)
    }
}