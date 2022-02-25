package com.example.androidinterntask


import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_info.view.*

class ItemAdapter(
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var currentItem = items[position]
        holder.itemView.apply {
            text_view_username.text = "Username: ".plus(currentItem.username)
            text_view_email.text = "Email: ".plus(currentItem.email)
            text_view_title.text = "Title: ".plus(currentItem.title)
            item_constraint_layout.background.setColorFilter(
                currentItem.color,
                PorterDuff.Mode.DARKEN
            ) //This method is depracated, but using BlendModeColorFilter requires API 29
            if (currentItem.url.isNotEmpty()) {
                Glide.with(this).load(currentItem.url).into(imageView)
            }
            //TODO Picture
            //TODO Needs a listener in the future
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun deleteItems() {
        items.clear()
        notifyDataSetChanged()
    }


}