package com.akmal.bmi_akmal_mahmudov.data.respository

import com.akmal.bmi_akmal_mahmudov.data.model.Person
import com.google.ads.AdRequest.Gender

interface PersonRepository {
    suspend fun generatePerson(name: String, weight: Int, height: Float, gender: Gender): Person
}