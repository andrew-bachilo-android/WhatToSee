package ru.lforb.mybooks.whattosee.LocalModel

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.lforb.mybooks.whattosee.RemoteModel.Movie
import javax.inject.Inject

class LocalModel @Inject constructor(@ApplicationContext context: Context) {
    private val dataBase: MovieDataBase = Room.databaseBuilder(
        context,
        MovieDataBase::class.java, "movie_db"
    ).build()

    suspend fun insertMovie(movie: Movie){
        dataBase.myMovieDao().insertMovie(movie)
    }

    suspend fun getAllMovies(): MutableList<Movie>{
        return dataBase.myMovieDao().getAllMovies()
    }

    suspend fun getAllTv(): MutableList<Movie>{
        return dataBase.myMovieDao().getAllTv()
    }
}