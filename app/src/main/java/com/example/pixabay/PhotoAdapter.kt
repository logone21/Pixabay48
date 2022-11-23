package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabay.databinding.ItemLayoutBinding
import com.example.pixabay.model.ImageModel


 class PhotoAdapter(val liste: ArrayList<ImageModel>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
  inner  class PhotoViewHolder( val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
        binding.photoImg.load(imageModel.largeImageURL)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
     holder.onBind(liste[position])
    }

    override fun getItemCount(): Int {
        return liste.size
    }
fun addlist(list: ImageModel){
    liste.add(list)
}

}