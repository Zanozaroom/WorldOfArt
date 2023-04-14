package com.example.search_inner.ui.utills

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.search_inner.databinding.ItemSearchBinding

interface OnClickImageSearch {
    fun onClickImageSearch(itemChicagoMuseumArt: CMArtWork)
}
class Searchdapter(private val onClickImageSearch: OnClickImageSearch) :
    ListAdapter<CMArtWork, Searchdapter.ArtWorksViewHolder>(ChicagoMuseumDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtWorksViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtWorksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtWorksViewHolder, position: Int) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        holder.populate(currentList[holder.absoluteAdapterPosition], onClickImageSearch)
        holder.itemView.animation = anim
    }

    class ArtWorksViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun populate(itemChicagoMuseumArt: CMArtWork, onClickImage: OnClickImageSearch) {
            binding.title.text = itemChicagoMuseumArt.title
            binding.itemImage.setOnClickListener {
                onClickImage.onClickImageSearch(itemChicagoMuseumArt)
            }

            Glide.with(binding.itemImage)
                .load(itemChicagoMuseumArt.image)
                .centerCrop()
                .error(com.example.api_model.R.drawable.not_image)
                .into(binding.itemImage)
        }

    }

    companion object {
        object ChicagoMuseumDiffUtil : DiffUtil.ItemCallback<CMArtWork>() {

            override fun areItemsTheSame(
                oldItem: CMArtWork,
                newItem: CMArtWork
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CMArtWork,
                newItem: CMArtWork
            ): Boolean = oldItem == newItem
        }
    }
}
