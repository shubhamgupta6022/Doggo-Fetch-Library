package com.sgupta.doggofetch.feature.breedlisting

import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sgupta.doggofetch.core.Resource
import com.sgupta.doggofetch.core.image.loader.ImageLoader
import com.sgupta.doggofetch.databinding.DogBreedItemLayoutBinding
import dagger.Lazy
import javax.inject.Inject

class DogBreedListAdapter @Inject constructor(
    private val imageLoader: Lazy<ImageLoader>
) : ListAdapter<String, DogBreedListAdapter.ViewHolder>(DogBreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DogBreedItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class ViewHolder(private val binding: DogBreedItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics
            val screenHeight = displayMetrics.heightPixels
            val newHeight = (screenHeight * 0.35).toInt()
            val layoutParams = binding.ivBreed.layoutParams
            layoutParams.height = newHeight
            binding.ivBreed.layoutParams = layoutParams
        }

        fun onBind(item: String) {
            with(binding) {
                imageLoader.get()
                    .fetch(item)
                    .into(this.ivBreed)
            }
        }
    }
}
