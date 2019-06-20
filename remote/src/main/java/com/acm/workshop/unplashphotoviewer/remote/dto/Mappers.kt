package com.acm.workshop.remote.dto

import com.acm.workshop.UnplashphotoViewer.data.entity.PhotoEntity


fun UnsplashPhoto.toPhotoEntity() = PhotoEntity(
    id = id,
    likes = likes,
    description = description,
    height = height,
    width = width,
    full = urls.full,
    regular = urls.regular,
    small = urls.small
)