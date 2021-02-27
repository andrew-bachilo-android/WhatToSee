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

    var API_KEY = "f31f6294f41a3b44682936d762ffafad"
    var URL_IMG = "https://image.tmdb.org/t/p/w780"
    lateinit var viewModel: MovieViewModel
    private var movies = mutableListOf<MyMovie>()
    private var tv = mutableListOf<MyMovie>()
    private var myText: TextView? = null
    private var myTextYear: TextView? = null
    private var myPoster: ImageView? = null
    private lateinit var progressBar: ProgressBar
    var rndMovie = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(activity as MainActivity).get(MovieViewModel::class.java)
        _binding = FragmentRandomMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("flat", viewModel.rating.toString())
        Log.d("flat", viewModel.genre.toString())
        Log.d("flat", viewModel.genreEx.toString())

        var rnds = (1..10).random()
        myText = requireActivity().findViewById<TextView>(R.id.text1)
        myTextYear = requireActivity().findViewById<TextView>(R.id.textViewYear)
        myPoster = requireActivity().findViewById<ImageView>(R.id.poster)
        progressBar = requireActivity().findViewById<ProgressBar>(R.id.progressBar)

        if(viewModel.movieType.equals("movie")){

            viewModel.getData(API_KEY, "ru-Ru",  false, false, rnds, viewModel.year, viewModel.rating, viewModel.genre, viewModel.genreEx, viewModel.country)

        }else{
            viewModel.getTvData(API_KEY, "ru-Ru", viewModel.year, false, false, rnds, viewModel.rating, viewModel.genre, viewModel.genreEx, viewModel.country)
        }

        viewModel.movieLive.observe(activity as MainActivity, Observer {
            movies.clear()
            movies.add(it)
            if (it.results.size > 0){
                rndMovie = (0 until  it.results.size).random()

                if(viewModel.movieType.equals("movie")){
                    myText?.text = it.results[rndMovie].title
                    myTextYear?.text = it.results[rndMovie].release_date.split("-")[0]
                }else{
                    myText?.text = it.results[rndMovie].name
                    myTextYear?.text = it.results[rndMovie].first_air_date.split("-")[0]
                }

                binding.textOverview.text = it.results[rndMovie].overview
                binding.textVote.text = it.results[rndMovie].vote_average.toString()

                Picasso.get ()
                    .load (URL_IMG.plus(it.results[rndMovie].poster_path))
                    .placeholder (R.drawable.movie)
                    .error (R.drawable.error_movie)
                    .into (myPoster);


                progressBar.visibility = GONE
            }else{
                myText?.text = "Ничего не найдено"
                myTextYear?.text = "Попробуйте еще раз"
            }


        })


        binding.btn.setOnClickListener {

            if (movies[0].results.size > 1){

                try {
                    movies[0].results.removeAt(rndMovie)
                    rndMovie = (0 until movies[0].results.size).random()
                    Picasso.get ()
                        .load (URL_IMG.plus(movies[0].results[rndMovie].poster_path))
                        .placeholder (R.drawable.movie)
                        .error (R.drawable.error_movie)
                        .into (myPoster);
                    binding.textOverview.text = movies[0].results[rndMovie].overview
                    binding.textVote.text = movies[0].results[rndMovie].vote_average.toString()
                    if (viewModel.movieType.equals("tv")){
                       myText?.text = movies[0].results[rndMovie].name
                        myTextYear?.text = movies[0].results[rndMovie].first_air_date.split("-")[0]
                    }else{
                        myText?.text = movies[0].results[rndMovie].title
                        myTextYear?.text = movies[0].results[rndMovie].release_date.split("-")[0]
                    }

                }
                catch (e:Exception){
                    Log.d("222", e.toString())
                }

            }else{
                rnds = (1..movies[0].total_pages).random()
                if(viewModel.movieType.equals("movie")){
                    viewModel.getData(API_KEY, "ru-Ru",  false, false, rnds,  viewModel.year, viewModel.rating, viewModel.genre, viewModel.genreEx, viewModel.country)
                }else{
                    viewModel.getTvData(API_KEY, "ru-Ru", viewModel.year, false, false, rnds, viewModel.rating, viewModel.genre, viewModel.genreEx, viewModel.country)
                }
            }

        }

        binding.btn2.setOnClickListener {
            if(viewModel.movieType.equals("tv")){
                val a = Movie("2020", movies[0].results[rndMovie].id, movies[0].results[rndMovie].name, movies[0].results[rndMovie].overview, movies[0].results[rndMovie].poster_path, "2020","title", movies[0].results[rndMovie].vote_average, 0)
                viewModel.insertMovie(a)
            }else{
                val a = Movie("2020",movies[0].results[rndMovie].id, "name", movies[0].results[rndMovie].overview, movies[0].results[rndMovie].poster_path,"2020", movies[0].results[rndMovie].title, movies[0].results[rndMovie].vote_average, 1)
                viewModel.insertMovie(a)
            }

        }

        binding.btnOver.setOnClickListener {
            binding.overviewLayout.visibility = VISIBLE
        }

        binding.btnCloseOver.setOnClickListener {
            binding.overviewLayout.visibility = GONE
        }



    }


}