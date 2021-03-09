package ru.lforb.mybooks.whattosee.RemoteModel


import android.util.Log
import java.lang.Exception
import javax.inject.Inject

class RemoteModel @Inject constructor(){
    private val apiService = ApiService.create()
    private val movieEmpty = MyMovie(1, mutableListOf(), 1, 1 )

    suspend fun getRemoteData(key:String, lang:String,  adult:Boolean, video:Boolean, page:Int, year:String,  vote:Int, genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String ):MyMovie{
        return try {
         val movies = apiService.getPosts(key, lang,  adult, video, page, year , vote, genre, withoutGenre, withLang)
            Log.d("!!!sdg", movies.toString())
            movies
        } catch (e: Exception){
            movieEmpty
        }
    }
    suspend fun getTvRemoteData(key:String, lang:String, year:String, adult:Boolean, video:Boolean, page:Int, vote:Int,  genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String):MyMovie{
        return try {
            val movies = apiService.getTvPosts(key, lang, year, adult, video, page, vote,  genre, withoutGenre, withLang)
            Log.d("!!!sdg", movies.toString())
            movies
        } catch (e: Exception){
            movieEmpty
        }
    }
}