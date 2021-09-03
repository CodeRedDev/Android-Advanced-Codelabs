package de.codereddev.fragments_communication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val STATE_FRAGMENT = "state_of_fragment"

abstract class BasicSongActivity : AppCompatActivity(), OnFragmentInteractionListener {
    protected var isFragmentDisplayed = false
    protected lateinit var fragmentToggleButton: Button

    protected var radioButtonChoice = SimpleFragment.NONE

    abstract val layoutResource:Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)

        fragmentToggleButton = findViewById(R.id.toggle_fragment_button)

        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                fragmentToggleButton.setText(R.string.close)
            }
        }

        fragmentToggleButton.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment == null) {
                fragmentToggleButton.setText(R.string.open)
                isFragmentDisplayed = false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }

    override fun onRadioButtonChoice(choice: Int) {
        radioButtonChoice = choice
        Toast.makeText(this, "Choice is $choice", Toast.LENGTH_SHORT).show()
    }

    private fun displayFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SimpleFragment.newInstance(radioButtonChoice))
            .addToBackStack(null)
            .commit()
        fragmentToggleButton.setText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val simpleFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            supportFragmentManager.beginTransaction().remove(simpleFragment).commit()
        }
        fragmentToggleButton.setText(R.string.open)
        isFragmentDisplayed = false
    }
}