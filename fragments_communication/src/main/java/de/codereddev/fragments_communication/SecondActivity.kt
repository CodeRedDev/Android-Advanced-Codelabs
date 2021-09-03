package de.codereddev.fragments_communication

import android.os.Bundle
import android.widget.Button

class SecondActivity : BasicSongActivity() {
    override val layoutResource: Int = R.layout.activity_second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.previous_button).setOnClickListener {
            finish()
        }
    }
}