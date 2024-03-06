package com.nohjason.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.retrofit.json.Root
import com.nohjason.retrofit.json.ViewModel
import com.nohjason.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel: ViewModel = viewModel()
        viewModel.marsUiState?.get(0)?.let { Text(
            text = it.baseDate,
        ) }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewModel.marsUiState?.let {
                items(count = it.size) { index ->
                    val test = viewModel.marsUiState?.get(index)
                    if (test != null) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            var value = test.obsrValue

                            when (test.category) {
                                "PTY" -> value = "모르겠다: $value"
                                "REH" -> value = "습도: ${value}%"
                                "RN1" -> value = "1시간 강수량: ${value}mm"
                                "T1H" -> value = "기온: ${value}\u2103"
                                "UUU" -> value = "동서바람성분: ${value}m/s"
                                "VEC" -> value = "풍향: ${value}deg"
                                "VVV" -> value = "남북바람성분: ${value}m/s"
                                "WSD" -> value = "풍속: ${value}m/s"
                            }
                            Text(text = value)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitTheme {
        Greeting()
    }
}