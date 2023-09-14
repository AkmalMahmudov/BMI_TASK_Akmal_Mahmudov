package com.akmal.bmi_akmal_mahmudov.domain

import com.akmal.bmi_akmal_mahmudov.R
import com.akmal.bmi_akmal_mahmudov.data.respository.PersonRepository
import com.akmal.bmi_akmal_mahmudov.utils.Constants
import com.akmal.bmi_akmal_mahmudov.utils.ResourceHelper
import com.akmal.bmi_akmal_mahmudov.utils.ResultData
import com.google.ads.AdRequest.Gender
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidationUseCase @Inject constructor(
    private val repo: PersonRepository,
    private val resourceHelper: ResourceHelper
) {

    fun validate(name: String, weight: Int, height: Int, gender: Int) = flow {
        if (name.isNotEmpty()) {
            val personHeight = height.toFloat() / 100
            val personGender = getGender(gender)

            emit(ResultData.Success(repo.generatePerson(name, weight, personHeight, personGender)))
        } else {
            emit(ResultData.Error(resourceHelper.getString(R.string.recheck)))
        }
    }

    private fun getGender(position: Int): Gender {
        return when (Constants.genders.getOrNull(position)?.uppercase()) {
            Gender.MALE.name -> Gender.MALE
            Gender.FEMALE.name -> Gender.FEMALE
            else -> Gender.UNKNOWN
        }
    }
}
