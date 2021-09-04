package de.codereddev.fragmentscommunicationdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import de.codereddev.fragmentscommunicationdetail.content.SongUtils

/**
 * An activity representing a list of song titles (items). When one is
 * touched, an intent starts [SongDetailActivity] representing
 * song details.
 */
class MainActivity : AppCompatActivity() {
    private var isTwoPane = false

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    /**
     * Sets up a song list as a RecyclerView.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        if (findViewById<FrameLayout>(R.id.song_detail_container) != null) {
            isTwoPane = true
        }

        // Set the toolbar as the app bar.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        // Get the song list as a RecyclerView.
        val recyclerView: RecyclerView = findViewById(R.id.song_list)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(SongUtils.SONG_ITEMS, isTwoPane)
    }
}