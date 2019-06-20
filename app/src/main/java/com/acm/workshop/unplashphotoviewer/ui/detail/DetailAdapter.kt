package com.acm.workshop.unplashphotoviewer.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder.DescriptionViewHolder
import com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder.CoverPhotoViewHolder
import com.acm.workshop.unplashphotoviewer.ui.detail.viewHolder.IconsViewHolder
import java.lang.Exception


class DetailAdapter(private val photo: Photo) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            FullPhotoViewType ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_photo_item, parent, false)
                return CoverPhotoViewHolder(view)
            }
            IconsViewType ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_icons_item, parent, false)
                return IconsViewHolder(view)
            }
            DescriptionViewType ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_description_item, parent, false)
                return DescriptionViewHolder(view)
            }
            else ->{
                throw Exception("DetailImage Invalid ViewType")
            }
        }
    }

    override fun getItemCount() = 3

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                FullPhotoViewType
            }
            1 -> {
                IconsViewType
            }
            2 -> {
                DescriptionViewType
            }
            else -> {
                throw Exception("DetailImage Invalid List Position")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        when(viewType){
            FullPhotoViewType ->{
                (holder as CoverPhotoViewHolder).bind(photo)
            }
            IconsViewType ->{
                (holder as IconsViewHolder).bind(photo)
            }
            DescriptionViewType ->{
                (holder as DescriptionViewHolder).bind(photo)
            }
        }
    }

    companion object {
        const val FullPhotoViewType = 1
        const val IconsViewType = 2
        const val DescriptionViewType = 3

    }

}