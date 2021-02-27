package ru.lforb.mybooks.whattosee.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.lforb.mybooks.whattosee.RemoteModel.Movie
import ru.lforb.mybooks.whattosee.RemoteModel.MyMovie
import ru.lforb.mybooks.whattosee.Repository.Repository

class MovieViewModel(val repository: Repository) : ViewModel() {
    val scope = CoroutineScope(Dispatchers.IO)

    var movieType = "movie"

    var flag = true

    var genre = mutableListOf<String>()

    var genreEx = mutableListOf<String>()

    val years = arrayListOf<String>()

    var rating = 5

    var country = ""

    var year = ""





    var favoriteMovie = mutableListOf<Movie>()

    var favoriteTv = mutableListOf<Movie>()

    val movieLive: MutableLiveData<MyMovie> by lazy {
        MutableLiveData<MyMovie>()
    }

    val genreChoice: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val genreChoiceEx: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val movie: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }




    fun getData(key:String, lang:String,  adult:Boolean, video:Boolean, page:Int, year:String, vote:Int, genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String){
        scope.launch {
            val data = repository.getData(key, lang,   adult, video, page, year, vote, genre, withoutGenre, withLang)
            Log.d("!!!", data.toString())
            movieLive.postValue(data)

        }
    }

    fun getTvData(key:String, lang:String, year:String, adult:Boolean, video:Boolean, page:Int, vote:Int, genre: MutableList<String>, withoutGenre: MutableList<String>, withLang:String){
        scope.launch {
            val data = repository.getTvData(key, lang, year, adult, video, page, vote, genre, withoutGenre, withLang)
            Log.d("!!!", data.toString())
            movieLive.postValue(data)

        }
    }
    fun insertMovie(newMovie:Movie){
        scope.launch {
            repository.insertMovie(newMovie)
            if(newMovie.isMovie == 1){
                favoriteMovie.add(newMovie)
            }else{
                favoriteTv.add(newMovie)
            }
        }
    }

    fun getAllMovies(){
        scope.launch {
            favoriteMovie.addAll(repository.getAllMovies())
        }
    }

    fun getAllTv(){
        scope.launch {
            favoriteTv.addAll(repository.getAllTv())
        }
    }
}

