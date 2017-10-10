package io.dastan.weather.adapter

import android.content.res.Resources
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import io.dastan.weather.R
import io.dastan.weather.model.DisplayCity
import io.dastan.weather.model.Values.City

class MainCityAdapter(private val res: Resources, val items: List<DisplayCity>, val callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val AddCell = 0
    val CityCell = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == AddCell) {
            return AddViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_add_city, parent, false))
        } else {
            return CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityViewHolder) {
            val item = items[position - 1]
            holder.icon.setImageURI(Uri.parse(String.format(res.getString(R.string.icon_url), item.country)))
            holder.title.text = item.name
            holder.subtitle.text = item.country

            holder.itemView.setOnClickListener { callback.onClick(position) }
        } else if (holder is AddViewHolder) {
            holder.button.setOnClickListener { callback.onClickAdd() }
        }
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) AddCell else CityCell
    }

    internal inner class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.button) as Button
    }

    internal inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title) as TextView
        var subtitle: TextView = itemView.findViewById(R.id.subtitle) as TextView
        var icon: SimpleDraweeView = itemView.findViewById(R.id.icon) as SimpleDraweeView
    }

    interface Callback {
        fun onClick(position: Int)
        fun onClickAdd()
    }
}
