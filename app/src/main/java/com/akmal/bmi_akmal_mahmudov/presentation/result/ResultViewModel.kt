package com.akmal.bmi_akmal_mahmudov.presentation.result

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.akmal.bmi_akmal_mahmudov.data.model.BMIResult
import com.akmal.bmi_akmal_mahmudov.data.model.Person
import com.akmal.bmi_akmal_mahmudov.domain.CalculateUseCase
import com.akmal.bmi_akmal_mahmudov.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val useCase: CalculateUseCase
) : BaseViewModel() {

    private var _result = MutableStateFlow(BMIResult("", "", "", ""))
    val result: StateFlow<BMIResult> = _result.asStateFlow()

    private var _screenshotSave = MutableStateFlow(Uri.EMPTY)
    val screenshotSave: StateFlow<Uri> = _screenshotSave.asStateFlow()

    init {
        calculate(savedStateHandle.get<Person>("person"))
    }

    private fun calculate(person: Person?) = viewModelScope.launch {
        useCase.calculate(person).onResult(
            success = { _result.emit(it) },
            error = { _messageFlow.emit(it) },
            loading = {}
        )
    }

    fun saveScreenshotToCache(bitmap: Bitmap) = viewModelScope.launch {
        useCase.saveToCache(bitmap).onResult(
            success = { _screenshotSave.emit(it) },
            error = { _messageFlow.emit(it) },
            loading = {})
    }

}