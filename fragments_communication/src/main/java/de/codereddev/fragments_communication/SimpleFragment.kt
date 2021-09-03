package de.codereddev.fragments_communication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SimpleFragment : Fragment() {
    var radioButtonChoice = NONE
    private var listener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw ClassCastException("$context ${resources.getString(R.string.exception_message)}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_simple, container, false)

        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group)

        if (requireArguments().containsKey(CHOICE_ARG)) {
            radioButtonChoice = requireArguments().getInt(CHOICE_ARG)
            if (radioButtonChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(radioButtonChoice).id)
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val index = radioGroup.indexOfChild(radioButton)
            val textView = rootView.findViewById<TextView>(R.id.fragment_header)

            when (index) {
                YES -> {
                    textView.setText(R.string.yes_message)
                    radioButtonChoice = YES
                    listener?.onRadioButtonChoice(YES)
                }
                NO -> {
                    textView.setText(R.string.no_message)
                    radioButtonChoice = NO
                    listener?.onRadioButtonChoice(NO)
                }
                else -> {
                    radioButtonChoice = NONE
                    listener?.onRadioButtonChoice(NONE)
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

    companion object {
        const val YES = 0
        const val NO = 1
        const val NONE = 2

        private const val CHOICE_ARG = "choice_arg"

        fun newInstance(choice: Int): SimpleFragment {
            val args = Bundle().apply {
                putInt(CHOICE_ARG, choice)
            }
            return SimpleFragment().apply {
                arguments = args
            }
        }
    }
}