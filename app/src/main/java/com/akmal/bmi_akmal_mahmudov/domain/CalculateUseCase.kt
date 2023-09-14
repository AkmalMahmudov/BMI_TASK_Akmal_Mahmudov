package com.akmal.bmi_akmal_mahmudov.domain

import android.graphics.Bitmap
import com.akmal.bmi_akmal_mahmudov.R
import com.akmal.bmi_akmal_mahmudov.data.model.BMI
import com.akmal.bmi_akmal_mahmudov.data.model.BMIResult
import com.akmal.bmi_akmal_mahmudov.data.model.Person
import com.akmal.bmi_akmal_mahmudov.data.respository.SaveRepository
import com.akmal.bmi_akmal_mahmudov.utils.ResourceHelper
import com.akmal.bmi_akmal_mahmudov.utils.ResultData
import com.akmal.bmi_akmal_mahmudov.utils.extensions.roundToDecimalPlaces
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalculateUseCase @Inject constructor(
    private val resourceHelper: ResourceHelper,
    private val repo: SaveRepository
) {

    fun calculate(person: Person?) = flow {
        if (person == null) {
            emit(ResultData.Error(resourceHelper.getString(R.string.sww)))
            return@flow
        }

        val bmi = calculateBMI(person.weight.toFloat(), person.height)
        val ponderalIndex = calculatePonderalIndex(person.weight.toFloat(), person.height)

        val greeting = resourceHelper.getString(R.string.greeting, person.name.uppercase())
        val status = resourceHelper.getString(R.string.status, getStatus(bmi))
        val ponderalText = resourceHelper.getString(R.string.index, ponderalIndex)

        emit(ResultData.Success(BMIResult("$greeting, $status", bmi.wholePart, bmi.fractionalPart, ponderalText)))
    }

    private fun calculateBMI(weight: Float, height: Float): BMI {
        val bmiValue = (weight / (height * height)).roundToDecimalPlaces()
        val dot = bmiValue.indexOf('.')
        if (bmiValue.substring(0, dot).toInt() > 100) {
            return BMI("99", bmiValue.substring(dot))
        }
        return BMI(bmiValue.substring(0, dot), bmiValue.substring(dot))
    }

    private fun calculatePonderalIndex(weight: Float, height: Float): String {
        return (weight / (height * height * height)).roundToDecimalPlaces()
    }

    private fun getStatus(bmi: BMI): String {
        val score = bmi.wholePart.toFloat() + bmi.fractionalPart.toFloat()
        return if (score < 18.0) resourceHelper.getString(R.string.underweight)
        else if (score > 25.0) resourceHelper.getString(R.string.overweight)
        else resourceHelper.getString(R.string.normal)
    }

    suspend fun saveToCache(bitmap: Bitmap) = repo.saveToCache(bitmap)

}