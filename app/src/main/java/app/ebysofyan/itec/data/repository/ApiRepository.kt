package app.ebysofyan.itec.data.repository

import app.ebysofyan.itec.core.base.PaginateResponse
import app.ebysofyan.itec.core.utils.NetworkConfig
import app.ebysofyan.itec.data.remote.api.ApiServices
import app.ebysofyan.itec.data.remote.model.Movie
import retrofit2.Call
import retrofit2.http.QueryMap

/**
 * Created by ebysofyan on 12/7/19.
 */
class ApiRepository {

    companion object {
        private var instance: ApiRepository? = null

        fun getInstance(): ApiRepository? {
            if (instance == null) {
                synchronized(ApiRepository::class.java) {
                    instance = ApiRepository()
                }
            }

            return instance
        }
    }

    fun fetchMovies(@QueryMap queryMap: HashMap<String, String> = hashMapOf()): Call<PaginateResponse<Movie>> {
        val service = NetworkConfig.client.create(ApiServices::class.java)
        return service.fetchMovies(queryMap = queryMap)
    }
}