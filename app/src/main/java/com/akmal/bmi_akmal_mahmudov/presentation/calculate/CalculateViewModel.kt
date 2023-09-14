package com.akmal.bmi_akmal_mahmudov.presentation.calculate

import androidx.lifecycle.viewModelScope
import com.akmal.bmi_akmal_mahmudov.data.model.Person
import com.akmal.bmi_akmal_mahmudov.domain.ValidationUseCase
import com.akmal.bmi_akmal_mahmudov.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculateViewModel @Inject constructor(private val userCase: ValidationUseCase) : BaseViewModel() {

    private var _isValid = MutableSharedFlow<Person>()
    val isValid: SharedFlow<Person> = _isValid.asSharedFlow()

    fun check(name: String, weight: Int, height: Int, gender: Int) = viewModelScope.launch {
        userCase.validate(name, weight, height, gender).onResult(
            success = { _isValid.emit(it) },
            error = { _messageFlow.emit(it) },
            loading = {}
        )
    }
}