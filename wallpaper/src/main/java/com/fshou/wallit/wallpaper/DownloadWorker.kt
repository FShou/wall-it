package com.fshou.wallit.wallpaper

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val photoUrl = inputData.getString("PHOTO_URL") ?: return Result.failure()

        return try {
            val fileName = photoUrl.substringAfterLast("/") // Extract file name from URL
            val downloadedFile = File(applicationContext.filesDir, fileName)

            if (!downloadedFile.exists()) {
                val url = URL(photoUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.inputStream.use { input ->
                    FileOutputStream(downloadedFile).use { output ->
                        input.copyTo(output)
                    }
                }
            }

            // Return the file path in the output data
            val outputData = Data.Builder()
                .putString("FILE_PATH", downloadedFile.absolutePath)
                .build()

            Result.success(outputData)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}