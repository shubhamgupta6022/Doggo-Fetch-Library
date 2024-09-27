package com.sgupta.doggofetch.feature.breedlisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.core.extensions.makeGone
import com.sgupta.doggofetch.core.extensions.makeVisible
import com.sgupta.doggofetch.databinding.FragmentDogBreedListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import dagger.Lazy

private const val ARG_NUMBER = "number"

@AndroidEntryPoint
class DogBreedListFragment : Fragment() {
    private var number: Int? = null

    private lateinit var binding: FragmentDogBreedListBinding
    @Inject
    lateinit var dogBreedAdapter : Lazy<DogBreedListAdapter>
    private val viewmodel : DogBreedListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(ARG_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedListBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        number?.let { viewmodel.getImages(it) }
    }

    private fun initObservers() {
        viewmodel.states
            .onEach {
                when(it) {
                    is Resource.Success -> {
                        dogBreedAdapter.get().submitList(it.data)
                        binding.progressBar.makeGone()
                    }
                    is Resource.Loading -> {
                        binding.progressBar.makeVisible()
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogBreedAdapter.get()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(number: Int) =
            DogBreedListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUMBER, number)
                }
            }
    }
}