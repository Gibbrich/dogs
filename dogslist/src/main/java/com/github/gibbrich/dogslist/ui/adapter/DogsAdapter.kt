package com.github.gibbrich.dogslist.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.gibbrich.core.model.Breed
import com.github.gibbrich.dogslist.R
import kotlinx.android.synthetic.main.layout_list_dogs_item.view.*

class DogsAdapter(
    albums: MutableList<Breed> = mutableListOf(),
    private val onDogClicked: (Breed) -> Unit
) : ConstantValueAdapter<Breed, DogsAdapter.Holder>(albums) {
    override fun createHolder(view: View): Holder =
        Holder(view, view.list_dogs_item_breed_image)

    override val lineResourceId = R.layout.layout_list_dogs_item

    override fun bind(holder: Holder, item: Breed, position: Int) {
        // todo - add placeholder for loading and error
        Glide.with(holder.itemView)
            .load(item.photoUrl)
            .centerCrop()
            .into(holder.picture)

        holder.picture.setOnClickListener { onDogClicked.invoke(item) }
    }

    class Holder(
        view: View,
        val picture: ImageView
    ) : RecyclerView.ViewHolder(view)
}