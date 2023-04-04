package com.hrudhaykanth116.schoolmanagement.common.data.datasources.remote

import android.util.Log
import com.hrudhaykanth116.schoolmanagement.common.data.models.DataResult
import com.hrudhaykanth116.schoolmanagement.common.data.models.UIText
import com.hrudhaykanth116.schoolmanagement.common.utils.network.OnlineTracker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class NetworkDataSource {

    private val dispatcher = Dispatchers.IO

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DataResult<T> =
        withContext(dispatcher) {

            if (OnlineTracker.isOnline) {
                try {
                    val response = call()
                    return@withContext if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null)
                            DataResult.Success(body)
                        else DataResult.Error(
                            UIText.Text("No data found.")
                        )
                    } else {
                        // TODO: Parse error
                        DataResult.Error(UIText.Text("Something went wrong"))
                    }

                } catch (e: Exception) {
                    Log.e(TAG, "getResult: ", e)
                    return@withContext DataResult.Error(
                        uiMessage = e.message?.let { UIText.Text(it) }
                    )
                }
            } else {
                Log.e(TAG, "getResult: No internet")
                return@withContext DataResult.Error(
                    uiMessage = UIText.Text("No internet"),
                    uiDescription = UIText.Text("Internet is not available."),
                )
            }
        }

    companion object {
        private const val TAG = "NetworkDataSource"
    }


}