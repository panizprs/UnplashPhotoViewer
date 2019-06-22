package com.acm.workshop.UnsplashPhotoViewer.local.datasource

import android.graphics.Bitmap
import android.os.Environment
import com.acm.workshop.UnsplashPhotoViewer.data.datasource.PhotoLocalDataSource
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class PhotoLocalDataSourceImpl : PhotoLocalDataSource {
    override fun saveImageFile(url: String?, fileName: String) {
        val photoFile = createNewImageFile(fileName)
        println("abs:  ${photoFile.absolutePath}")
        Picasso.get().load(url).get().also { capturedBitmap ->
            //          BitmapFactory.decodeStream(URL(photoURL).openConnection().getInputStream()).also { capturedBitmap ->
            try {
                val out = FileOutputStream(photoFile)
                println(out.fd)
                capturedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }
    }

    private fun createNewImageFile(fileName: String): File {
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ),
            "UnsplashPhotos"
        )
        if (!storageDir.exists())
            storageDir.mkdir()

        return File(storageDir, "$fileName.jpg")

    }

}