package com.acm.workshop.UnsplashPhotoViewer.data.datasource

interface PhotoLocalDataSource{
    fun saveImageFile(url: String?, fileName: String)
}