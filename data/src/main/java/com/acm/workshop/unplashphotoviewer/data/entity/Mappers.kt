package com.acm.workshop.UnplashphotoViewer.data.entity

import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo

fun PhotoEntity.toPhoto() = Photo(
    id = id,
    width = width,
    height = height,
    small = small,
    regular = regular,
    full = full,
    description = description,
    likes = likes
)