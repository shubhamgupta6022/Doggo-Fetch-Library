package com.sgupta.doggofetch.feature.breedlisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogBreedListViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
): ViewModel() {

    private var _states : MutableSharedFlow<Resource<List<String>>> = MutableSharedFlow()
    val states = _states.asSharedFlow()

    fun getImages(number: Int) {
        getImagesUseCase.invoke(GetImagesUseCase.Param(number))
            .onEach {
                _states.emit(it)
            }
            .launchIn(viewModelScope)
    }

}