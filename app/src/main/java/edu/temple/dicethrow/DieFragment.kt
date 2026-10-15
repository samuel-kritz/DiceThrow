package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {

    //val only has getter, not setter --> delegate
    //lazy is function taking lambda
    //get() invokes lambda, only once
    //never accessed unless attached to parent activity - requireContext() is properly scoped
    val  dieViewModel : DieViewModel by lazy {

        //initiate to access ViewModel...provider is the activity
        //this instead of Require Context = fragments have their own context/rolls are different
        ViewModelProvider(this)[DieViewModel::class.java]
    }

    val CURRENT_ROLL = "currentroll"

    lateinit var dieTextView: TextView

    var dieSides: Int = 6
//    var currentRoll: Int = 0

    //"arguments" is different bundle than savedInstanceState bundle.
    //same every time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
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

        dieViewModel.getRoll().observe(viewLifecycleOwner) { rollValue ->
            dieTextView.text = rollValue.toString()
        }

        view.setOnClickListener {
            if (requireActivity() is DieRollInterface) {
                (requireActivity() as DieRollInterface).rollDice()
            }
        }
    }




    fun throwDie() {
        dieViewModel.setRoll(Random.nextInt(dieSides)+1)
    }


    companion object {
        fun newInstance(dieSides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, dieSides)
            }
        }
    }


    interface DieRollInterface {
        fun rollDice()
    }
}


