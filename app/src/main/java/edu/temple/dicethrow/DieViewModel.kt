package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel: ViewModel() {  //stores behavior/ops

    //move current roll logic here
    private val currentRoll = MutableLiveData<Int>().apply {
        value = 0
    }

    //live data object: not Int; USES Int
    //not changing live data object, rather the objects its storing
    fun setRoll (roll: Int) {
        currentRoll.value = roll
    }

    //this returns live data <INT>, not Int.
    //updates would go in setRoll()
    //kotlin shortcut, but notice the type/wrapper
    fun getRoll(): LiveData<Int> = currentRoll



}


