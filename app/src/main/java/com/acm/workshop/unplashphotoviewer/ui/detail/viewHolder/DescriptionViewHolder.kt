package com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R

class DescriptionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val descriptionTextView = itemView.findViewById<TextView>(R.id.desc)

    fun bind(photo : Photo){
        descriptionTextView.text = photo.description
    }

}