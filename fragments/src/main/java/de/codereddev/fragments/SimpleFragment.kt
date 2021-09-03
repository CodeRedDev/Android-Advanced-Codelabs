package de.codereddev.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

private val YES = 0
private val NO = 1

class SimpleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)

        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(R.id.fragment_header)

            when (index) {
                YES -> textView.setText(R.string.yes_message)
                NO -> textView.setText(R.string.no_message)
                else -> {
                }
            }
        }

        val ratingBar = rootView.findViewById<RatingBar>(R.id.rating_bar)
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            val floatString = rating.toString()

            Toast.makeText(
                requireContext(),
                getString(R.string.rating_toast, floatString),
                Toast.LENGTH_SHORT
            ).show()
        }

        return rootView
    }
}