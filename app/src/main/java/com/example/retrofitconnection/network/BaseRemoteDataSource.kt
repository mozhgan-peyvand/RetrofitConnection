package io.github.metmuseum.themet.common.network

import com.example.retrofitconnection.network.Resource
import retrofit2.Response


class BaseRemoteDataSource {
     fun <T> checkApiResult(response: Response<T>): Resource<T> {

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                return Resource.Success(body)
        }

        return Resource.Error(
            Exceptions.RemoteDataSourceException(
                response.code().toString(),
                response.message()
            )
        )
    }
}
