package com.example.retrofitconnection

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
            val resultList = remember {
                mutableStateListOf<Result>()
            }

            val remoteDataSource =
                RemoteDataSource(RetrofitHelper.getInstance().create(RetrofitService::class.java))
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
                                    Log.i("TAG", result.data.toString())
                                    resultList.addAll(result.data.results)
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
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(resultList) {
                            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = it.author)
                                    Text(text = it.dateAdded)
                                }
                                Text(text = it.authorSlug)
                            }
                            Divider(modifier = Modifier.height(4.dp))
                        }
                    }
                }

            }
        }
    }
}
