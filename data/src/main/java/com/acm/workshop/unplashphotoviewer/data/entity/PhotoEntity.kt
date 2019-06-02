package com.acm.workshop.UnplashphotoViewer.data.entity

data class PhotoEntity(
    val id: String,
    val width: Int,
    val height: Int,
    val likes: Int,
    val description: String?,

//    urls
    val small: String?,
    val full: String?
)