package com.examples.medicinetracker.affirmations.ui.main.facts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.medicinetracker.affirmations.network.*
import com.examples.medicinetracker.affirmations.ui.main.AffirmationApiStatus
import com.examples.medicinetracker.affirmations.ui.main.AffirmationImageApiStatus
import com.examples.medicinetracker.affirmations.ui.main.Status
import kotlinx.coroutines.launch

enum class FactsStatus {LOADING, ERROR, DONE}
private const val TAG = "Facts"

class FactsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _factsStatus = MutableLiveData<FactsStatus>()
    val factStatus: LiveData<FactsStatus> = _factsStatus

    private var _facts = MutableLiveData<List<Fact>>()
    val facts: LiveData<List<Fact>> = _facts

    init {
        getFacts()
    }

    private fun getFacts() {
        viewModelScope.launch {
            _factsStatus.value = FactsStatus.LOADING
            try {
                var listOfFacts = mutableListOf<Fact>()
                for (i in 1..10){
                    val oneFact: Fact = FactsApi.retrofitService.getContents().contents
                    listOfFacts.add(oneFact)
                }
                _facts.value = listOfFacts
                _factsStatus.value = FactsStatus.DONE
            } catch (e: Exception) {
                _factsStatus.value = FactsStatus.ERROR
                _facts = MutableLiveData()
            }
        }
    }
}