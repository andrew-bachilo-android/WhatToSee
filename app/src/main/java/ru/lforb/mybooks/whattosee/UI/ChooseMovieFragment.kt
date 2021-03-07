package ru.lforb.mybooks.whattosee.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils.getTextArray
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ru.lforb.mybooks.whattosee.MainActivity
import ru.lforb.mybooks.whattosee.R
import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModel
import ru.lforb.mybooks.whattosee.databinding.FragmentChooseMovieBinding

import java.lang.StringBuilder
import java.util.*


class ChooseMovieFragment : Fragment() {
    private var _binding: FragmentChooseMovieBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(activity as MainActivity).get(MovieViewModel::class.java)
        _binding = FragmentChooseMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        //Radio group movie type

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
           if (i == R.id.movies_radio) {
               viewModel.movieType = "movie"
               binding.textGenreItems.text = ""
               binding.textGenreItems2.text = ""
            }else{
               viewModel.movieType = "tv"
               binding.textGenreItems.text = ""
               binding.textGenreItems2.text = ""
           }
        }

        // Dialog genre items

        binding.buttonAlertGenre.setOnClickListener {
            viewModel.flag = true
            Log.d("flag", viewModel.flag.toString())
            if (viewModel.movieType == "movie"){
                val myDialogFragment = GenresDialogFragment()
                val manager = (activity as MainActivity).supportFragmentManager
                myDialogFragment.show(manager, "myDialog")

            }else{
                val myDialogFragment = GenresTvDialogFragment()
                val manager = (activity as MainActivity).supportFragmentManager
                myDialogFragment.show(manager, "myDialog")
            }
        }

        // Dialog without genres items

        binding.buttonAlertGenre2.setOnClickListener {
            viewModel.flag = false
            Log.d("flag", viewModel.flag.toString())
            if (viewModel.movieType == "movie"){
                val myDialogFragment = GenresDialogFragment()
                val manager = (activity as MainActivity).supportFragmentManager
                myDialogFragment.show(manager, "myDialog2")
            }else{
                val myDialogFragment = GenresTvDialogFragment()
                val manager = (activity as MainActivity).supportFragmentManager
                myDialogFragment.show(manager, "myDialog2")
            }

        }

        //Spinner countries

        binding.spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> viewModel.country = ""
                    1 -> viewModel.country = "en"
                    2 -> viewModel.country = "ru"
                    3 -> viewModel.country = "de"
                    4 -> viewModel.country = "fr"
                    5 -> viewModel.country = "es"
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //Spinner year

        val year = Calendar.getInstance().get(Calendar.YEAR)
        viewModel.years.add("Любой")
        for(i in year downTo 1960){
            viewModel.years.add(i.toString())
        }

        val adapter = ArrayAdapter(
            activity as MainActivity,
            android.R.layout.simple_spinner_item,
            viewModel.years
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.spinnerYear.adapter = adapter;
        binding.spinnerYear.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View?, position: Int, id: Long){
                when(position){
                    0 -> viewModel.year = ""
                    else -> viewModel.year = "${parent.getItemAtPosition(position)}-01-01"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>){

            }
        }


        viewModel.genreChoice.observe(activity as MainActivity, Observer {
            binding.textGenreItems.text = it.toString()
        })

        viewModel.genreChoiceEx.observe(activity as MainActivity, Observer {
            binding.textGenreItems2.text = it.toString()
        })

        binding.btnShowMovie.setOnClickListener {
            viewModel.rating = binding.ratingBar.rating.toInt()
            (activity as MainActivity).navController.navigate(R.id.randomMovieFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.genre.clear()
        viewModel.genreEx.clear()
        binding.textGenreItems.text = ""
        binding.textGenreItems2.text = ""
    }

}