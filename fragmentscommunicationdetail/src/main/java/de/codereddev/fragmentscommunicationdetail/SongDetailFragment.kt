package de.codereddev.fragmentscommunicationdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.codereddev.fragmentscommunicationdetail.content.SongUtils

class SongDetailFragment : Fragment() {
    // SongItem includes the song title and detail.
    private var song: SongUtils.Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(SongUtils.SONG_ID_KEY)) {
            song = SongUtils.SONG_ITEMS[requireArguments().getInt(SongUtils.SONG_ID_KEY)]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.song_detail, container, false)

        if (song != null) (rootView.findViewById<TextView>(R.id.song_detail)).text = song!!.details

        return rootView
    }

    companion object {
        fun newInstance(songId: Int) = SongDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(SongUtils.SONG_ID_KEY, songId)
            }
        }
    }
}