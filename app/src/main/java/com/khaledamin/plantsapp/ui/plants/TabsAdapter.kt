package com.khaledamin.plantsapp.ui.plants

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.ItemTabBinding

class TabsAdapter(val data: Map<String, String>,val tabCallback: TabCallback) :
    Adapter<TabsAdapter.TabsViewHolder>() {
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    inner class TabsViewHolder(val binding: ItemTabBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                tabCallback.onTabClicked(data.values.stream().toList()[layoutPosition])
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabsViewHolder {
        return TabsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_tab,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onBindViewHolder(holder: TabsViewHolder, position: Int) {
        holder.binding.tab = data.keys.stream().toList()[position]
    }
}