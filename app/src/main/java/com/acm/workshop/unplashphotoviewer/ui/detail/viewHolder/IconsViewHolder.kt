package com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnsplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.detail.OnDownloadPhotoClickListener

class IconsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val likesTextView = itemView.findViewById<TextView>(R.id.likes)
    private val downloadIcon = itemView.findViewById<ImageButton>(R.id.download)

    fun bind(photo : Photo, onDownloadPhotoClickListener: OnDownloadPhotoClickListener){
        likesTextView.text = photo.likes.toString()

        downloadIcon.setOnClickListener {
            photo.full?.let{url ->
                onDownloadPhotoClickListener.onDownloadIconClicked()
            }
        }
    }

}