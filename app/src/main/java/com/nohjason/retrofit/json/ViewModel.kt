package com.nohjason.retrofit.json

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val TAG = "sex"

class ViewModel : ViewModel() {
    var marsUiState: List<Item>? by mutableStateOf(null)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        val now: LocalDate = LocalDate.now()
        val date = DateTimeFormatter.ofPattern("yyyyMMdd")
        val dateNow = now.format(date)

        viewModelScope.launch {
            val result = Api.retrofitService.getData(date = "$dateNow")
            marsUiState = result.response.body.items.item

            Log.d(TAG, "${result}")
        }
    }
}