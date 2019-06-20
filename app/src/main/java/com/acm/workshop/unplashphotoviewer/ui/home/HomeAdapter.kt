package com.acm.workshop.unplashphotoviewer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.squareup.picasso.Picasso






class HomeAdapter(private val onPhotoClickListener: OnPhotoClickListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private var photos = mutableListOf<Photo>()

    fun updatePhotos(list: List<Photo>) {
        photos = list.toMutableList()
        notifyDataSetChanged()
    }


    fun addPhotos(newPhotos : List<Photo>){
        val prvSize = photos.size
        photos.addAll(newPhotos)
        notifyItemRangeChanged(prvSize, photos.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(photos[position], onPhotoClickListener)
    }


    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val image = itemView.findViewById<ImageView>(R.id.image)


        fun bind(photo : Photo, onPhotoClickListener: OnPhotoClickListener){
            Picasso.get().load(photo.small).into(image)

            image.setOnClickListener {
                onPhotoClickListener.onPhotoClicked(photo)
            }
        }
    }
}