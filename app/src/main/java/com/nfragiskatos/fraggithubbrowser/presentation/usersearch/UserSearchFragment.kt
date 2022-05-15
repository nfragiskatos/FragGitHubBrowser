package com.nfragiskatos.fraggithubbrowser.presentation.usersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nfragiskatos.fraggithubbrowser.databinding.FragmentUserSearchLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSearchFragment : Fragment() {

    private val viewModel: UserSearchViewModel by viewModels<UserSearchViewModel>()
    private lateinit var binding: FragmentUserSearchLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSearchLayoutBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        return binding.root
    }
}