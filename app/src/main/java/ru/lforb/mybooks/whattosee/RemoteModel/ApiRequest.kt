package ru.lforb.mybooks.whattosee.RemoteModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
val BASE_URL = "https://api.themoviedb.org/3/"
interface ApiService {
    @GET("discover/movie")
    suspend fun getPosts(
        @Query("api_key") key:String,
        @Query("language") lang:String,
        @Query("include_adult") adult:Boolean,
        @Query("include_video") video:Boolean,
        @Query("page") page:Int,
        @Query("primary_release_date.gte") year: String,
        @Query("vote_average.gte") vote:Int,
        @Query("with_genres") genre: MutableList<String>,
        @Query("without_genres") withoutGenre: MutableList<String>,
        @Query("with_original_language") withLang: String

    ): MyMovie

    @GET("discover/tv")
    suspend fun getTvPosts(
        @Query("api_key") key:String,
        @Query("language") lang:String,
        @Query("first_air_date.gte") year: String,
        @Query("include_adult") adult:Boolean,
        @Query("include_video") video:Boolean,
        @Query("page") page:Int,
        @Query("vote_average.gte") vote:Int,
        @Query("with_genres") genre: MutableList<String>,
        @Query("without_genres") withoutGenre: MutableList<String>,
        @Query("with_original_language") withLang: String

    ): MyMovie


    companion object Factory {
        fun create(): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}