package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), DieFragment.DieRollInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //needed ONLY if fragment added via transaction
        //can also search by fragment tag
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) !is DieFragment) {
            supportFragmentManager
                .beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragmentContainerView, DieFragment.newInstance(6))
                .add(R.id.fragmentContainerView2, DieFragment.newInstance(6))
                .commit()

            //no guarantee what happened to fragment, so use this


        }
    }

    override fun rollDice() {
        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            (supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView) as DieFragment).throwDie()

            (supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView2) as DieFragment).throwDie()
        }
    }
}