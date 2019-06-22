package com.acm.workshop.unplashphotoviewer.ui.home

import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo

interface OnPhotoClickListener{
    fun onPhotoClicked(photo : Photo)
}