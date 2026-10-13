package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {

    val CURRENT_ROLL = "currentroll"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int = 0

    //"arguments" is different bundle than savedInstanceState bundle.
    //same every time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        savedInstanceState?.let {
            it.getInt(CURRENT_ROLL).run {
                currentRoll = this
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (currentRoll == 0) {
            throwDie()
        } else {
            dieTextView.text = currentRoll.toString()
        }
        view.setOnClickListener {
            throwDie()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_ROLL, currentRoll)
    }

    fun throwDie() {
//        dieTextView.text = (Random.nextInt(dieSides)+1).toString()
        currentRoll = (Random.nextInt(dieSides)+1)
        dieTextView.text = currentRoll.toString()
    }


    companion object {
        fun newInstance(dieSides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, dieSides)
            }
        }
    }
}


