package ru.lforb.mybooks.whattosee.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class MyMovie(
    val page:Int,
    val results:MutableList<Movie>,
    val total_pages:Int,
    val total_results:Int
    )

@Entity(tableName = "movies")
data class Movie(
    val first_air_date:String,
    @PrimaryKey val id:Int,
    val name:String,
    val overview:String,
    val poster_path:String,
    val release_date:String,
    val title:String,
    val vote_average:Double,
    val isMovie:Int
    )



