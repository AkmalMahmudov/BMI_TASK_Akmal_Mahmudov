package com.akmal.bmi_akmal_mahmudov.data.respository.impl

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.akmal.bmi_akmal_mahmudov.R
import com.akmal.bmi_akmal_mahmudov.data.respository.SaveRepository
import com.akmal.bmi_akmal_mahmudov.utils.ResultData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class SaveRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) : SaveRepository {
    override suspend fun saveToCache(bitmap: Bitmap): Flow<ResultData<Uri>> = flow {

        val fileName = "screenshot.png"
        val cacheDir = context.cacheDir
        val file = File(cacheDir, fileName)

        try {
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { fos ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                    fos.flush()
                    fos.close()
                }
            }
            emit(
                ResultData.Success(
                    FileProvider.getUriForFile(
                        context, context.packageName + ".fileprovider", file
                    )
                )
            )
        } catch (e: IOException) {
            Timber.e(e)
            e.printStackTrace()
        }
        emit(ResultData.Error(context.getString(R.string.sww)))
    }

    fun saveScreenshotToCache(bitmap: Bitmap, context: Context): Uri? {
        val fileName = "screenshot.png"
        val cacheDir = context.cacheDir
        val file = File(cacheDir, fileName)

        try {
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
                fos.close()
            }
            return FileProvider.getUriForFile(
                context,
                context.packageName + ".fileprovider",
                file
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}