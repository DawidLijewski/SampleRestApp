package lijewski.songapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lijewski.domain.entity.Song
import lijewski.songapp.R
import lijewski.songapp.databinding.ItemSongBinding
import java.util.*

class SongListAdapter : RecyclerView.Adapter<SongListAdapter.SongItemViewHolder>() {
    private val songList = ArrayList<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSongBinding>(layoutInflater, R.layout.item_song, parent, false)
        return SongItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongItemViewHolder, position: Int) {
        val song = songList[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    fun updateSongList(songList: List<Song>?) {
        if (songList == null || songList.isEmpty()) {
            this.songList.clear()
            notifyDataSetChanged()
        } else {
            this.songList.addAll(0, songList)
            notifyItemInserted(0)

        }
    }

    inner class SongItemViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.songObject = song
        }
    }
}
