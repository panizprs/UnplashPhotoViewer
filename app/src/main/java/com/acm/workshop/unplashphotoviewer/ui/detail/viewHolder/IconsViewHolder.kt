package com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R

class IconsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val likesTextView = itemView.findViewById<TextView>(R.id.likes)

    fun bind(photo : Photo){
        likesTextView.text = photo.likes.toString()
    }

}