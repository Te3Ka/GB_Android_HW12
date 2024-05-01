package ru.te3ka.homework12

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isSearching = MutableLiveData<Boolean>()
    var isSearching: LiveData<Boolean> = _isSearching

    private val _searchResult = MutableLiveData<String?>()
    val searchResult: LiveData<String?> = _searchResult

    init {
        _isSearching.value = false
        _searchResult.value = null
    }

    fun onButtonSearchClick(searchText: String) {
        if (searchText.length >= 3 && !_isSearching.value!!) {
            _isSearching.value = true
            viewModelScope.launch {
                Log.d("Button Search", "searching $searchText")
                delay(3_000)
                Log.d("Button Search", "$searchText not found")
                _isSearching.value = false
                _searchResult.value = null
            }
        }
    }
}