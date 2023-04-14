package com.example.museumart_inner.utill

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.museumart_inner.databinding.ItemMuseumListBinding
import com.example.api_model.click.OnClickImageOnAdapterArtWork

class CMArtWorkAdapter(private val onClickOnImage: OnClickImageOnAdapterArtWork) :
    ListAdapter<CMArtWork, CMArtWorkAdapter.ArtWorksViewHolder>(ChicagoMuseumDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtWorksViewHolder {
        val binding = ItemMuseumListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtWorksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtWorksViewHolder, position: Int) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        holder.populate(currentList[position], onClickOnImage)
        holder.itemView.animation = anim
    }

    class ArtWorksViewHolder(private val binding: ItemMuseumListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun populate(itemChicagoMuseumArt: CMArtWork, onClickOnImage: OnClickImageOnAdapterArtWork) {
            binding.titleArt.text = itemChicagoMuseumArt.title
            Glide.with(binding.imageArt)
                .load(itemChicagoMuseumArt.image)
                .centerCrop()
                .error(com.example.api_model.R.drawable.not_image)
                .into(binding.imageArt)

            binding.imageArt.setOnClickListener {
                onClickOnImage.onClickOnImage(itemChicagoMuseumArt)
            }
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
