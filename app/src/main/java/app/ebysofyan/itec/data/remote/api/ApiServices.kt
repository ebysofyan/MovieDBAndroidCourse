package app.ebysofyan.itec.data.remote.api

import app.ebysofyan.itec.core.base.PaginateResponse
import app.ebysofyan.itec.core.utils.Constants
import app.ebysofyan.itec.data.remote.model.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by ebysofyan on 12/7/19.
 */
interface ApiServices {

    @GET("/3/movie/popular")
    fun fetchMovies(@Query("api_key") apiKey: String = Constants.API_KEY, @QueryMap queryMap: HashMap<String, String> = hashMapOf()): Call<PaginateResponse<Movie>>

    @GET("/3/movie/popular")
    suspend fun fetchMovies1(@Query("api_key") apiKey: String = Constants.API_KEY, @QueryMap queryMap: HashMap<String, String> = hashMapOf()): Response<PaginateResponse<Movie>>
}