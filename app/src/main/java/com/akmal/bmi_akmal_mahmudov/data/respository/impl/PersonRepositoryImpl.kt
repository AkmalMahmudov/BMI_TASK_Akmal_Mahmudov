package com.akmal.bmi_akmal_mahmudov.data.respository.impl

import com.akmal.bmi_akmal_mahmudov.data.model.Person
import com.akmal.bmi_akmal_mahmudov.data.respository.PersonRepository
import com.google.ads.AdRequest.Gender
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor() : PersonRepository {
    override suspend fun generatePerson(
        name: String, weight: Int, height: Float, gender: Gender
    ): Person {
        return Person(0, name, weight, height, gender)
    }
}