package com.acm.workshop.unplashphotoviewer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.acm.workshop.UnplashPhotoViewer.domain.model.Photo
import com.acm.workshop.unplashphotoviewer.R
import com.squareup.picasso.Picasso
import android.widget.Toast





class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    private var photos = mutableListOf<Photo>()

    fun addPhotos(newPhotos : List<Photo>){
        photos.addAll(newPhotos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(photos[position])
    }



    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val image = itemView.findViewById<ImageView>(R.id.image)


        fun bind(photo : Photo){
            Picasso.get().load(photo.small).into(image)
        }
    }
}