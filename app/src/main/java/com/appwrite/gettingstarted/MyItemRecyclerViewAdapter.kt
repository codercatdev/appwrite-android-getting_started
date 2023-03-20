package com.appwrite.gettingstarted

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appwrite.gettingstarted.databinding.FragmentItemBinding
import io.appwrite.models.Country


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
  private val values: List<Country>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(
      FragmentItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = item.code
    holder.contentView.text = item.name
  }

  override fun getItemCount(): Int = values.size

  inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
      return super.toString() + " '" + contentView.text + "'"
    }
  }

}
