package de.codereddev.fragmentscommunicationdetail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import de.codereddev.fragmentscommunicationdetail.content.SongUtils

/**
 * An activity representing a single song detail screen.
 */
class SongDetailActivity : AppCompatActivity() {
    // SongItem includes the song title and detail.
    var mSong: SongUtils.Song? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // This activity displays the detail. In a real-world scenario,
        // get the data from a content repository.
        mSong = SongUtils.SONG_ITEMS[intent.getIntExtra(SongUtils.SONG_ID_KEY, 0)]
        // Show the detail information in a TextView.
        if (mSong != null) (findViewById<TextView>(R.id.song_detail)).text = mSong!!.details
    }

    /**
     * Performs action if the user selects the Up button.
     *
     * @param item Menu item selected (Up button)
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown.
            // NavUtils allows users to navigate up one level in the
            // application structure.
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}