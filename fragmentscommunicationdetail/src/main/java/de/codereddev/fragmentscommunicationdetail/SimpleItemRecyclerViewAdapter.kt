package de.codereddev.fragmentscommunicationdetail

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.codereddev.fragmentscommunicationdetail.content.SongUtils

class SimpleItemRecyclerViewAdapter(
    private val values: List<SongUtils.Song>,
    private val isTwoPane: Boolean
) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    /**
     * This method inflates the layout for the song list.
     * @param parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View.
     * @return
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleItemRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_list_content, parent, false)
        return ViewHolder(view)
    }

    /**
     * This method implements a listener with setOnClickListener().
     * When the user taps a song title, the code checks if mTwoPane
     * is true, and if so uses a fragment to show the song detail.
     * If mTwoPane is not true, it starts SongDetailActivity
     * using an intent with extra data about which song title was selected.
     *
     * @param holder   ViewHolder
     * @param position Position of the song in the array.
     */
    override fun onBindViewHolder(holder: SimpleItemRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.item = values[position]
        holder.idView.text = (position + 1).toString()
        holder.contentView.text = values[position].song_title
        holder.view.setOnClickListener { v ->
            val context = v.context
            if (!isTwoPane) {
                val intent = Intent(
                    context,
                    SongDetailActivity::class.java
                )
                intent.putExtra(
                    SongUtils.SONG_ID_KEY,
                    holder.adapterPosition
                )
                context.startActivity(intent)
            } else {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.song_detail_container,
                        SongDetailFragment.newInstance(holder.adapterPosition)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    /**
     * Get the count of song list items.
     * @return
     */
    override fun getItemCount(): Int {
        return values.size
    }

    /**
     * ViewHolder describes an item view and metadata about its place
     * within the RecyclerView.
     */
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById<View>(R.id.id) as TextView
        val contentView: TextView = view.findViewById<View>(R.id.content) as TextView
        var item: SongUtils.Song? = null
    }
}