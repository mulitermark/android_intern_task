package com.example.androidinterntask


import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidinterntask.databinding.ItemInfoBinding

class ItemAdapter(
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        holder.apply {
            binding.textViewUsername.text = itemView.context.getString(R.string.username).plus(currentItem.username)
            binding.textViewEmail.text = itemView.context.getString(R.string.email).plus(currentItem.email)
            binding.textViewTitle.text = itemView.context.getString(R.string.title).plus(currentItem.title)
            binding.itemConstraintLayout.background.setColorFilter(
                currentItem.color,
                PorterDuff.Mode.DARKEN
            ) //This method is deprecated, but using BlendModeColorFilter requires API 29
            Glide.with(this.itemView)
                .load(currentItem.url)
                .error(R.drawable.logo).centerCrop()
                .into(binding.imageView)
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
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    /** Adds an [item] to the adapter's [items] list. */
    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    /** Deletes all items from the adapter's [items] list. */
    fun deleteItems() {
        items.clear()
        notifyDataSetChanged()
    }


}