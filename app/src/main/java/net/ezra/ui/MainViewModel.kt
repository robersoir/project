package net.ezra.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.ezra.navigation.ROUTE_START
import net.ezra.navigation.ROUTE_VIEW_PROD
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val myPreferencesDataStore: MyPreferencesDataStore
):ViewModel(){
    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(ROUTE_START)
        private set

    init{
        myPreferencesDataStore.readAppEntry.onEach { loadStartScreen ->
         startDestination = if (loadStartScreen){
             ROUTE_START} else {
                 ROUTE_VIEW_PROD
         }
            delay(300)
            isLoading = false

        } .launchIn(viewModelScope)
    }

    fun saveAppEntry(){
        viewModelScope.launch {
            myPreferencesDataStore.saveAppEntry()
        }
    }
}