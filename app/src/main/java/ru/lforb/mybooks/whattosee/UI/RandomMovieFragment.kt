package ru.lforb.mybooks.whattosee.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.lforb.mybooks.whattosee.MainActivity
import ru.lforb.mybooks.whattosee.R
import ru.lforb.mybooks.whattosee.RemoteModel.Movie
import ru.lforb.mybooks.whattosee.RemoteModel.MyMovie
import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModel
import ru.lforb.mybooks.whattosee.databinding.FragmentRandomMovieBinding

class RandomMovieFragment : Fragment() {
    private var _binding: FragmentRandomMovieBinding? = null
    private val binding get() = _binding!!
    private var API_KEY = "f31f6294f41a3b44682936d762ffafad"
    private var URL_IMG = "https://image.tmdb.org/t/p/w780"
    lateinit var viewModel: MovieViewModel
    private var movies = mutableListOf<Movie>()
    private var tv = mutableListOf<MyMovie>()
    var randomMovie = 0
    var randomPageMovie = (1..10).random()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity as MainActivity).get(MovieViewModel::class.java)
        _binding = FragmentRandomMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

        viewModel.movieLive.observe(activity as MainActivity, Observer {
            movies.clear()
            tv.clear()
            tv.add(it)
            for(i in tv[0].results){
                if(i.overview.length > 3){
                    movies.add(i)
                }
            }
           displayRandomMovie()
        })

        binding.btnNext.setOnClickListener {
            movies.removeAt(randomMovie)
            displayRandomMovie()
        }

        binding.btnFavorite.setOnClickListener {
            addFavorite()
        }

        binding.btnOver.setOnClickListener {
            binding.overviewLayout.visibility = VISIBLE
        }

        binding.btnCloseOver.setOnClickListener {
            binding.overviewLayout.visibility = GONE
        }
    }

    fun getData(){
        if(viewModel.movieType.equals("movie")){

            viewModel.getData(API_KEY, "ru-Ru",  false, false, randomPageMovie, viewModel.year, viewModel.rating, viewModel.genre, viewModel.genreEx, viewModel.country)

        }else{
            viewModel.getTvData(API_KEY, "ru-Ru", viewModel.year, false, false, randomPageMovie, viewModel.rating,  viewModel.genre, viewModel.genreEx, viewModel.country)
        }

    }

    fun addFavorite(){
        if(viewModel.movieType.equals("tv")){
            val a = Movie("2020", movies[randomMovie].id, movies[randomMovie].name, movies[randomMovie].overview, movies[randomMovie].poster_path, "2020","title", movies[randomMovie].vote_average, 0)
            viewModel.insertMovie(a)
        }else{
            val a = Movie("2020",movies[randomMovie].id, "name", movies[randomMovie].overview, movies[randomMovie].poster_path,"2020", movies[randomMovie].title, movies[randomMovie].vote_average, 1)
            viewModel.insertMovie(a)
        }
        Toast.makeText(activity,"Добавлено в 'Мои фильмы'", Toast.LENGTH_SHORT).show()
    }

    fun displayRandomMovie(){
        if (movies.size > 1){
            try {
                randomMovie = (0 until  movies.size).random()

                if(viewModel.movieType.equals("movie")){
                    binding.textTitle.text = movies[randomMovie].title
                    binding.textViewYear.text = movies[randomMovie].release_date.split("-")[0]
                }else{
                    binding.textTitle.text = movies[randomMovie].name
                    binding.textViewYear.text = movies[randomMovie].first_air_date.split("-")[0]
                }

                binding.textOverview.text = movies[randomMovie].overview
                binding.textVote.text = movies[randomMovie].vote_average.toString()

                Picasso.get ()
                    .load (URL_IMG.plus(movies[randomMovie].poster_path))
                    .placeholder (R.drawable.movie)
                    .error (R.drawable.error_movie)
                    .into (binding.poster);
                binding.progressBar.visibility = GONE
            }
            catch (e:Exception){
                Log.d("222", e.toString())
            }
        }else{
            randomPageMovie = (1..tv[0].total_pages).random()
            getData()
        }
    }
}