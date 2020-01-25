package app.ebysofyan.itec.core.base

import com.google.gson.annotations.SerializedName

/**
 * Created by ebysofyan on 12/7/19.
 */

data class PaginateResponse<T>(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    val results: List<T>
)

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val statusCode: Int, val detail: String) : ApiResponse<Nothing>()
    data class Error(val e: Exception) : ApiResponse<Nothing>()
}
