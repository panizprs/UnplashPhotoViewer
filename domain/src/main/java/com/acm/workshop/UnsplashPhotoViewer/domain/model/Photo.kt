package com.acm.workshop.UnsplashPhotoViewer.domain.model

data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    val likes: Int,
    val description: String?,

//    urls
    val small: String?,
    val regular: String?,
    val full: String?
)