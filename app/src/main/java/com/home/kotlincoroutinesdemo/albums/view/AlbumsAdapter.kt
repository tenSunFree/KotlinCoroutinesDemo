package com.home.kotlincoroutinesdemo.albums.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.kotlincoroutinesdemo.R
import com.home.kotlincoroutinesdemo.albums.model.local.AlbumsEntity
import kotlinx.android.synthetic.main.activity_albums_item.view.*

class AlbumsAdapter(
    private val albumsList: ArrayList<AlbumsEntity>
) : RecyclerView.Adapter<AlbumsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: AlbumsEntity) {
            itemView.text_iew_id.text = user.id.toString()
            itemView.text_iew_title.text = user.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_albums_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = albumsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(albumsList[position])

    fun addData(list: List<AlbumsEntity>) {
        albumsList.addAll(list)
    }
}