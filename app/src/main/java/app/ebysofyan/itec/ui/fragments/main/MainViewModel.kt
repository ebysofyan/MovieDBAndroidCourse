package app.ebysofyan.itec.ui.fragments.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import app.ebysofyan.itec.core.base.PaginateResponse
import app.ebysofyan.itec.data.remote.model.Movie
import app.ebysofyan.itec.data.repository.ApiRepository
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.QueryMap
import java.io.IOException

class MainViewModel : ViewModel() {
    private val apiRepository = ApiRepository.getInstance()
    private var moviesLiveData: MutableLiveData<PaginateResponse<Movie>> = MutableLiveData()

    fun fetchMovies(@QueryMap queryMap: HashMap<String, String> = hashMapOf()): LiveData<PaginateResponse<Movie>> {
        apiRepository?.fetchMovies(queryMap = queryMap)?.enqueue(object :
            Callback<PaginateResponse<Movie>> {
            override fun onFailure(call: Call<PaginateResponse<Movie>>, t: Throwable) {
                Log.e("onFailure", Gson().toJson(t.message))
            }

            override fun onResponse(
                call: Call<PaginateResponse<Movie>>,
                response: Response<PaginateResponse<Movie>>
            ) {
                when {
                    response.isSuccessful -> {
                        Log.e("response.isSuccessful", "Success")
                        moviesLiveData.value = response.body()
                    }
                    else -> {
                        Log.e("response.isFailed", "Failed")
                        moviesLiveData.value = null
                    }
                }
            }
        })
        return this.moviesLiveData
    }

    // With coroutine scope
    fun fetchMovies1(@QueryMap queryMap: HashMap<String, String> = hashMapOf()): LiveData<PaginateResponse<Movie>> {
        GlobalScope.launch {
            val response = apiRepository?.fetchMovies1(queryMap = queryMap)
            try {
                when (response?.isSuccessful) {
                    true -> {
                        Log.e("response.isSuccessful", "Success")
                        moviesLiveData.value = response.body()
                    }
                    else -> {
                        Log.e("response.isFailed", "Failed")
                        moviesLiveData.value = null
                    }
                }
            } catch (e: IOException) {
                Log.e("response.isFailure", "Failure")
            }
        }
        return this.moviesLiveData
    }

    // With coroutine scope + livedata-ktx
    fun fetchMovies2(@QueryMap queryMap: HashMap<String, String> = hashMapOf()) = liveData {
        val response = apiRepository?.fetchMovies1(queryMap = queryMap)
        try {
            emit(response)
        } catch (e: IOException) {
            Log.e("response.isFailure", "Failure")
            emit(null)
        }
    }
}
