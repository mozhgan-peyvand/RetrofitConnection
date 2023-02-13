package com.example.retrofitconnection

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofitconnection.ui.theme.RetrofitConnectionTheme
import io.github.metmuseum.themet.common.network.Exceptions
import io.github.metmuseum.themet.common.network.NetworkHandler
import com.example.retrofitconnection.network.Resource
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val remoteDataSource = RemoteDataSource( RetrofitHelper.getInstance().create(QuotesApi::class.java))
            RetrofitConnectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val networkHandler = NetworkHandler(LocalContext.current)
                    // launching a new coroutine
                    coroutineScope.launch {
                        if (networkHandler.hasNetworkConnection()) {
                            when (val result = remoteDataSource.getQuotesList()) {
                                is Resource.Success -> {
                                    Log.d("mozhgan",result.data.toString())
                                    Resource.Success(result.data)
                                }
                                is Resource.Error -> Resource.Error(
                                    Exceptions.RemoteDataSourceException(
                                        result.error.toString()
                                    )
                                )
                            }
                        } else
                            Resource.Error(Exceptions.NetworkConnectionException())
                    }
                }

                }
            }
        }
    }

@Composable
fun Greeting(name: String) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitConnectionTheme {
        Greeting("Android")
    }
}