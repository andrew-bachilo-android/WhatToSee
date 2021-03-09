package ru.lforb.mybooks.whattosee.Repository

import ru.lforb.mybooks.whattosee.LocalModel.LocalModel
import ru.lforb.mybooks.whattosee.RemoteModel.Movie
import ru.lforb.mybooks.whattosee.RemoteModel.MyMovie
import ru.lforb.mybooks.whattosee.RemoteModel.RemoteModel
import javax.inject.Inject

class Repository @Inject constructor(val remoteModel: RemoteModel, val localModel: LocalModel){
    suspend fun getData(key:String, lang:String,  adult:Boolean, video:Boolean, page:Int, year:String, vote:Int, genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String ):MyMovie{
        var movieList = remoteModel.getRemoteData(key, lang, adult, video, page, year, vote, genre, withoutGenre, withLang )
        return movieList
    }

    suspend fun getTvData(key:String, lang:String, year:String, adult:Boolean, video:Boolean, page:Int, vote:Int, genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String): MyMovie {
        var movieList = remoteModel.getTvRemoteData(key, lang, year, adult, video, page, vote,  genre, withoutGenre, withLang)
        return movieList
    }

    suspend fun insertMovie(newMovie: Movie){
        localModel.insertMovie(newMovie)
    }

    suspend fun getAllMovies(): MutableList<Movie>{
        return localModel.getAllMovies()
    }

    suspend fun getAllTv(): MutableList<Movie>{
        return localModel.getAllTv()
    }
}