package com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.squareup.picasso.Picasso


class CoverPhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val coverPhotoImageView = itemView.findViewById<ImageView>(R.id.coverImage)

    fun bind(photo: Photo){
        Picasso.get().load(photo.regular).into(coverPhotoImageView)
    }

}