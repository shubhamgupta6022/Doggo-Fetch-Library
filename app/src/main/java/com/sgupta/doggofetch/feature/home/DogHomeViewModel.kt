package com.sgupta.doggofetch.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.domain.usecase.GetImageUseCase
import com.sgupta.doggofetch.domain.usecase.GetNextImageUseCase
import com.sgupta.doggofetch.domain.usecase.GetPreviousImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogHomeViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase,
    private val getNextImageUseCase: GetNextImageUseCase,
    private val getPreviousImageUseCase: GetPreviousImageUseCase
): ViewModel() {

    private var _states: MutableSharedFlow<Resource<String>> = MutableSharedFlow()
    val states = _states.asSharedFlow()

    private var currentIndex = 0

    fun getCurrentItemIndex() = currentIndex

    fun getImage() {
        currentIndex = 1
        getImageUseCase.invoke()
            .onEach {
                _states.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun getNextImage() {
        currentIndex ++
        getNextImageUseCase.invoke()
            .onEach {
                _states.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun getPreviousImage() {
        currentIndex --
        getPreviousImageUseCase.invoke()
            .onEach {
                _states.emit(it)
            }
            .launchIn(viewModelScope)
    }

}