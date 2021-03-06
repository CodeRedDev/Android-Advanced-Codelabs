package de.codereddev.fragments_communication

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : BasicSongActivity() {

    override val layoutResource: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.next_button).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}