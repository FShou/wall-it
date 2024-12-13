package com.fshou.wallit.wallpaper

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import coil3.load
import com.fshou.wallit.wallpaper.databinding.ActivityWallpaperBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.io.File

class WallpaperActivity : AppCompatActivity() {
    private val viewModel: WallpaperViewModel by viewModel()
    private val binding by lazy { ActivityWallpaperBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        loadKoinModules(wallpaperModule)
        val photoUrl = intent.getStringExtra(PHOTO_URL)

        if (photoUrl != null) {
            downloadPhoto(photoUrl)
        }

        viewModel.downloadedPhotoPath.observe(this) { filePath ->
            if (filePath != null) {
                binding.setupWallpaperPreview(filePath)
            }
        }
    }

    private fun downloadPhoto(photoUrl: String) {
        val workManager = WorkManager.getInstance(this)

        val data = Data.Builder()
            .putString("PHOTO_URL", photoUrl)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(data)
            .build()

        workManager.enqueue(downloadRequest)
        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this) { workInfo ->
            if (workInfo != null) {
                if (workInfo.state == WorkInfo.State.ENQUEUED)
                    Toast.makeText(this, "Downloading the Wallpaper", Toast.LENGTH_SHORT).show()
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    workInfo.outputData.getString("FILE_PATH")
                        ?.let { viewModel.setDownloadedPath(it) }
                }
            }
        }
    }

    private fun ActivityWallpaperBinding.setupWallpaperPreview(filePath: String) {
        val file = File(filePath)
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)

        ivPreview.load(bitmap)
        println(filePath + "HEHEHEHE")
        button.setOnClickListener {
            setWallpaper(
                filePath,
                WallpaperManager.FLAG_SYSTEM
            )
        }
        button2.setOnClickListener {
            setWallpaper(
                filePath,
                WallpaperManager.FLAG_LOCK
            )
        }
        button3.setOnClickListener { setWallpaper(filePath, null) }
    }


    private fun setWallpaper(filePath: String, flag: Int?) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(applicationContext)
            val file = File(filePath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)

                if (flag != null) {
                    wallpaperManager.setBitmap(bitmap, null, true, flag)
                } else {
                    wallpaperManager.setBitmap(bitmap)
                }

                Toast.makeText(this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to set wallpaper: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        const val PHOTO_URL = "photo-url"
    }
}