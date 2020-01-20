package com.bertan.randomuser.feature.presentation.view.adapter

import android.graphics.Color
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertan.randomuser.R
import com.bertan.randomuser.feature.presentation.view.data.UserViewData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_view_item.view.*

class UserAdapter(
    private val onItemClick: (UserViewData) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var items: List<UserViewData> = emptyList()

    fun setItems(items: List<UserViewData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            itemView = from(parent.context).inflate(R.layout.user_view_item, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userViewData = items[position]
        holder.itemView.run {
            setOnClickListener { onItemClick(userViewData) }

            name.text = userViewData.displayName
            email.text = userViewData.email
            card.setBackgroundColor(Color.parseColor(userViewData.backgroundColor))

            Glide.with(this)
                .load(userViewData.thumbnail)
                .circleCrop()
                .into(imageView)
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}