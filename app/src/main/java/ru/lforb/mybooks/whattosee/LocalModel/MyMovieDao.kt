package ru.lforb.mybooks.whattosee.LocalModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.lforb.mybooks.whattosee.RemoteModel.Movie

@Dao
interface MyMovieDao {

    @Query("SELECT * FROM movies WHERE isMovie = 1")
    suspend fun getAllMovies():MutableList<Movie>

    @Query("SELECT * FROM movies WHERE isMovie = 0")
    suspend fun getAllTv():MutableList<Movie>

    @Insert
    suspend fun insertMovie(movie:Movie)
}