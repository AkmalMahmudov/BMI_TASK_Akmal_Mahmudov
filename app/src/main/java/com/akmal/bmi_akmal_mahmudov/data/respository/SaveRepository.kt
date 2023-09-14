package com.akmal.bmi_akmal_mahmudov.data.respository

import android.graphics.Bitmap
import android.net.Uri
import com.akmal.bmi_akmal_mahmudov.utils.ResultData
import kotlinx.coroutines.flow.Flow

interface SaveRepository {
    suspend fun saveToCache(bitmap: Bitmap): Flow<ResultData<Uri>>
}