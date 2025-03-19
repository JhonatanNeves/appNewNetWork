package com.example.appnewnetwork.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.model.Post

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){

    var items: List<Post> = mutableListOf()
        class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(post: Post) {
                itemView.findViewById<ImageView>(R.id.home_img_post).setImageURI(post.uri)
                itemView.findViewById<TextView>(R.id.home_txt_coments).text = post.caption
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
            return FeedViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
            holder.bind(items[position])
        }

    }