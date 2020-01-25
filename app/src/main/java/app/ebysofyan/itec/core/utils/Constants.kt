package app.ebysofyan.itec.core.utils

/**
 * Created by ebysofyan on 12/7/19.
 */
object Constants {

    const val API_KEY = "27d588f44884cfc30f97f76d26d9a55a"

    fun buildImageUrl(size: String = "w185", fileName: String): String {
        if (fileName.startsWith("/")) fileName.replace("/", "")
        return "https://image.tmdb.org/t/p/$size/$fileName"
    }
}