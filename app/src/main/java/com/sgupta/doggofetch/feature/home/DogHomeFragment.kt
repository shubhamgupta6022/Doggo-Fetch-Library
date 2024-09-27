package com.sgupta.doggofetch.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sgupta.doggofetch.R
import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.core.extensions.makeGone
import com.sgupta.doggofetch.core.extensions.makeVisible
import com.sgupta.doggofetch.core.image.loader.ImageLoader
import com.sgupta.doggofetch.databinding.FragmentDogHomeBinding
import com.sgupta.doggofetch.feature.breedlisting.DogBreedListFragment
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DogHomeFragment : Fragment() {

    private lateinit var binding: FragmentDogHomeBinding
    private val viewmodel: DogHomeViewModel by viewModels()

    @Inject
    lateinit var imageLoader: Lazy<ImageLoader>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogHomeBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserves()
        viewmodel.getImage()
    }

    private fun initObserves() {
        viewmodel.states
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        updateViews(it.data)
                        binding.progressBar.makeGone()
                    }

                    is Resource.Loading -> {
                        binding.progressBar.makeVisible()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun updateViews(url: String) {
        imageLoader.get()
            .fetch(url)
            .into(binding.ivDog)
        setPreviousNextVisibility()
    }

    private fun setPreviousNextVisibility() {
        if (viewmodel.getCurrentItemIndex() <= 1) {
            binding.ivPrevious.makeGone()
        } else {
            binding.ivPrevious.makeVisible()
        }
        binding.ivNext.makeVisible()
    }

    private fun initViews() {
        binding.ivNext.setOnClickListener {
            viewmodel.getNextImage()
        }
        binding.ivPrevious.setOnClickListener {
            viewmodel.getPreviousImage()
        }
        binding.btnSubmit.setOnClickListener {
            if (binding.inputField.text.toString().isNotEmpty()) {
                val number = binding.inputField.text.toString().toInt()
                activity?.supportFragmentManager?.beginTransaction()?.add(
                    R.id.frame_layout,
                    DogBreedListFragment.newInstance(number)
                )?.addToBackStack(null)?.commit()
                binding.inputField.text.clear()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DogHomeFragment()
    }
}