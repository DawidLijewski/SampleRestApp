package lijewski.songapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lijewski.domain.entity.SearchResult
import lijewski.songapp.R
import lijewski.songapp.databinding.ItemSongBinding
import java.util.*

class ResultsListAdapter(private val onItemClickedListener: OnItemClickedListener) : RecyclerView.Adapter<ResultsListAdapter.SongItemViewHolder>() {
    private val resultList = ArrayList<SearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSongBinding>(layoutInflater, R.layout.item_song, parent, false)
        return SongItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val result = resultList[position]
        holder.bind(result)

        holder.itemView.setOnClickListener {
            onItemClickedListener.onItemClicked(result, position)
        }
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateSongList(searchResultList: List<SearchResult>?) {
        if (searchResultList == null || searchResultList.isEmpty()) {
            this.resultList.clear()
            notifyDataSetChanged()
        } else {
            this.resultList.addAll(0, searchResultList)
            notifyItemInserted(0)

        }
    }

    inner class SongItemViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchResult: SearchResult) {
            binding.searchResultObject = searchResult
        }
    }

    /**
     * Listener for item click interactions.
     */
    interface OnItemClickedListener {
        fun onItemClicked(result: SearchResult, position: Int)
    }
}