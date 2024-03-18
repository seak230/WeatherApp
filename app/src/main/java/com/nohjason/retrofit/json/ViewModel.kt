package com.nohjason.retrofit.json

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
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
        val clock: LocalTime = LocalTime.now()

        val date = DateTimeFormatter.ofPattern("yyyyMMdd")
        val time = DateTimeFormatter.ofPattern("HHmm")

        val dateNow = now.format(date)
        val timeNow = clock.format(time)
        Log.d(TAG, "getMarsPhotos: $timeNow")

        viewModelScope.launch {
            val result = Api.retrofitService.getData(date = "$dateNow", time = "$timeNow")
            marsUiState = result.response.body.items.item

            Log.d(TAG, "${result}")
        }
    }
}