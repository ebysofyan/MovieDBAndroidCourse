package app.ebysofyan.itec.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by ebysofyan on 12/7/19.
 */

@Parcelize
data class Movie(
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = mutableListOf(),
    val id: Int? = 0,
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int? = 0
) : Parcelable