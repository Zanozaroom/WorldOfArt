package com.example.favorite_inner.utill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.favorite_inner.databinding.ItemFavoriteArtBinding
import com.example.api_model.click.OnClickImageOnAdapterArtWork

class CMFavoriteArtAdapter(private val onClickFavoriteArt: OnClickImageOnAdapterArtWork) :
    ListAdapter<CMArtWork, CMFavoriteArtAdapter.ArtWorksViewHolderFavoriteArt>(
        DiffUtilChicagoMuseumFavoriteArt()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtWorksViewHolderFavoriteArt {
        val binding =
            ItemFavoriteArtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtWorksViewHolderFavoriteArt(binding)
    }

    override fun onBindViewHolder(holder: ArtWorksViewHolderFavoriteArt, position: Int) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        holder.populate(currentList[position], onClickFavoriteArt)
        holder.itemView.animation = anim
    }

    class ArtWorksViewHolderFavoriteArt(
        private val binding: ItemFavoriteArtBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun populate(artItem: CMArtWork, itemClickListener: OnClickImageOnAdapterArtWork) {
            binding.imageArtFavorite.setOnClickListener {
                itemClickListener.onClickOnImage(artItem)
            }
            binding.titleArt.text = artItem.title
            Glide.with(binding.imageArtFavorite)
                .load(artItem.image)
                .centerCrop()
                .error(com.example.api_model.R.drawable.not_image)
                .into(binding.imageArtFavorite)
        }
    }

    companion object {
        class DiffUtilChicagoMuseumFavoriteArt : DiffUtil.ItemCallback<CMArtWork>() {
            override fun areItemsTheSame(oldItem: CMArtWork, newItem: CMArtWork): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: CMArtWork, newItem: CMArtWork): Boolean =
                oldItem == newItem
        }
    }
}
