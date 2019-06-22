package com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.detail.OnPhotoLoaded
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class CoverPhotoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val coverPhotoImageView = itemView.findViewById<ImageView>(R.id.coverImage)

    fun bind(photo: Photo, onPhotoLoaded: OnPhotoLoaded){
        Picasso.get().load(photo.regular).into(coverPhotoImageView, object : Callback{
            override fun onSuccess() {
                onPhotoLoaded.onPhotoLoaded()
            }

            override fun onError(e: Exception?) {

            }

        })
    }

}