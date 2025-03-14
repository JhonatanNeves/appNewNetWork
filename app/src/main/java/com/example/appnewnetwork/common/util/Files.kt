package com.example.appnewnetwork.common.util

import android.app.Activity
import com.example.appnewnetwork.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

object Files {

    private const val FILENAME_FORMAT =  "yyyy-mm-dd-HH-mm-ss-SSS"
    fun generateFile(activity: Activity) : File {
        val mediaDir = activity.externalMediaDirs.firstOrNull()?.let {
            File(it, activity.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        val outPutDir = if (mediaDir != null && mediaDir.exists())
            mediaDir else activity.filesDir

        return File(outPutDir, SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".jpg")
    }

}