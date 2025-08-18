package kr.co.rewview.clone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.rewview.clone.databinding.LayoutActivityLogItemBinding

class ActivityLogListAdapter : RecyclerView.Adapter<ActivityLogListAdapter.ActivityLogViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityLogViewHolder {
        return ActivityLogViewHolder(LayoutActivityLogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ActivityLogViewHolder, position: Int) {
        holder.bind("")
    }

    override fun getItemCount(): Int = 1

    inner class ActivityLogViewHolder(private val binding: LayoutActivityLogItemBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: String) {

        }
    }
}
