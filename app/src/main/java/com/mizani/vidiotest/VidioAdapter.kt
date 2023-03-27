package com.mizani.vidiotest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_vidio.view.*

class VidioAdapter(
    private val episodes: List<Episode>,
    private val action: (episode: Episode)  -> Unit
) : RecyclerView.Adapter<VidioAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(episode: Episode) {
            view.row_title.text = episode.title
            view.row_desc.text = episode.description
            when (episode.downloadStatus) {
                DownloadStatus.NOT_DOWNLOADED -> {
                    view.row_button.visibility = View.VISIBLE
                    view.row_progress.visibility = View.GONE
                }
                DownloadStatus.DOWNLOADING -> {
                    view.row_button.visibility = View.GONE
                    view.row_progress.visibility = View.VISIBLE
                }
                DownloadStatus.DOWNLOAD_FINISH -> {
                    view.row_button.visibility = View.GONE
                    view.row_progress.visibility = View.GONE
                }
            }
            view.row_button.setOnClickListener {
                action.invoke(episode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_vidio, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size
}