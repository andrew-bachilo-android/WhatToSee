package ru.lforb.mybooks.whattosee.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.lforb.mybooks.whattosee.MainActivity
import ru.lforb.mybooks.whattosee.R
import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModel
import ru.lforb.mybooks.whattosee.databinding.FragmentFavoritesTvBinding
import ru.lforb.mybooks.whattosee.databinding.FragmentStartBinding


class FavoritesTvFragment : Fragment() {
    private var _binding: FragmentFavoritesTvBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MovieViewModel
    lateinit var navController: NavController
    lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(activity as MainActivity).get(MovieViewModel::class.java)
        _binding = FragmentFavoritesTvBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        adapter = FavoriteAdapter(viewModel.favoriteTv)
        binding.recyclerFavoriteTv.adapter = adapter
        binding.recyclerFavoriteTv.layoutManager = GridLayoutManager(context,2)


    }
}