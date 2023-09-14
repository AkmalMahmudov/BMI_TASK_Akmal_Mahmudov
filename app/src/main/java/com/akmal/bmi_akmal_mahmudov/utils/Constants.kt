package com.akmal.bmi_akmal_mahmudov.utils

object Constants {

    const val map_api_key = "AIzaSyAQOAxykhvgU5lXJ7kRHCXj1zEJOne9TAc"
    const val user_client_type = "user"
    const val guest_client_type = "guest"

    const val ad_id1 = "ca-app-pub-3940256099942544/1033173712"
    const val ad_id2 = "ca-app-pub-3940256099942544/2247696110"
    const val play_store = "https://play.google.com/"
    const val share_title = "Share Screenshot"
    const val minRange = 18
    const val maxRange = 25

    const val pickerMax = 200
    const val pickerMin = 1

    const val genderMax = 1
    const val genderMin = 0
    val genders = arrayOf("male", "female")

    private var arr: Array<String>? = null

    fun getArr(): Array<String> {
        if (arr == null) {
            arr = generateArray().toTypedArray()
        }
        return arr!!
    }

    private fun generateArray(): List<String> {
        val tempList = ArrayList<String>()
        for (i in 1..300) {
            tempList.add(i.toString())
        }
        return tempList
    }
}