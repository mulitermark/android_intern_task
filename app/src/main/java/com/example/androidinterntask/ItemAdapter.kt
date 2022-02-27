package com.example.androidinterntask


import android.content.Intent
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidinterntask.databinding.ActivityDescriptionBinding
import com.example.androidinterntask.databinding.ItemInfoBinding

class ItemAdapter(
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root)

//    private lateinit var binding: ItemInfoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var currentItem = items[position]

        holder.apply {
            binding.textViewUsername.text = "Username: ".plus(currentItem.username)
            binding.textViewEmail.text = "Email: ".plus(currentItem.email)
            binding.textViewTitle.text = "Title: ".plus(currentItem.title)
            binding.itemConstraintLayout.background.setColorFilter(
                currentItem.color,
                PorterDuff.Mode.DARKEN
            ) //This method is depracated, but using BlendModeColorFilter requires API 29
            if (currentItem.url.isNotEmpty()) {
                Glide.with(this.itemView).load(currentItem.url).placeholder(android.R.drawable.ic_delete)
                    .error(android.R.drawable.ic_delete).into(binding.imageView)
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DescriptionActivity::class.java)
            intent.putExtra("username", currentItem.username)
                .putExtra("email", currentItem.email)
                .putExtra("title", currentItem.title)
                .putExtra("url", currentItem.url)
                .putExtra("created", currentItem.created)
                .putExtra("description", currentItem.description)
                .putExtra("guid", currentItem.guid)
                .putExtra("color", currentItem.color)
            //TODO this doesn't seem correct
            holder.itemView.context.startActivity(intent)
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