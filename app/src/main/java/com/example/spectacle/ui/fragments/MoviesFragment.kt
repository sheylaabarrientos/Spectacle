package com.example.spectacle.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.spectacle.FailSystemActivity
import com.example.spectacle.R
import com.example.spectacle.domain.Movie
import com.example.spectacle.onclick.MovieListener
import com.example.spectacle.ui.adapter.CategoryAdapter
import com.example.spectacle.ui.adapter.MoviesAdapter
import com.example.spectacle.ui.model.MoviesViewModel

class MoviesFragment : Fragment(), MovieListener {

    private lateinit var listAdapter: MoviesAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovies = view.findViewById<RecyclerView>(R.id.rcvContainer)
        val rvGenres = view.findViewById<RecyclerView>(R.id.rcvAllMoviesTypes)

        categoryAdapter = CategoryAdapter(context = view.context, listener = this)
        listAdapter = MoviesAdapter(context = view.context, listener = this)
        rvMovies.adapter = listAdapter
        rvGenres.adapter = categoryAdapter

        moviesViewModel = ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
        moviesViewModel.getPopularMovies()
        moviesViewModel.getGenres()
        progressBar = view.findViewById(R.id.loading)

        observeGenres()
        observeListMovies()
        observeViewState()

//        rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int): Unit {
//                var layout =
//                    (rvMovies.layoutManager as LinearLayoutManager).findViewByPosition((rvMovies.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
//
//                val indexOfToggleButton = 2
//                val indexOfId = 5
//
//                if (layout is ConstraintLayout) {
//                    if (layout.getChildAt(indexOfId) is MaterialTextView) {
//                        val movieIdString =
//                            (layout.getChildAt(indexOfId) as MaterialTextView).text.toString()
//                        val movieId = Integer.parseInt(movieIdString)
//
//                        (layout.getChildAt(indexOfToggleButton) as AppCompatToggleButton).isChecked =
//                            MoviesViewModel.movieIdIsFavorite(movieId) == true
//                    }
//                }
//            }
//        })
    }

    override fun onResume() {
        super.onResume()
        listAdapter.notifyDataSetChanged()
    }

    private fun observeListMovies() {
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let {
                listAdapter.dataSet.clear()
                listAdapter.dataSet.addAll(it)
                listAdapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun observeGenres() {
        moviesViewModel.categoryListLiveData.observe(viewLifecycleOwner, { response ->
            response?.let {
                categoryAdapter.dataset.addAll(it)
                categoryAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeViewState() {
        moviesViewModel.viewStateLiveData.observe(viewLifecycleOwner) { result ->
            if (result == ViewState.GeneralError) {
                val intent = Intent(requireContext(), FailSystemActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun openMovieDetails(movieId: Int) {
        TODO("Not yet implemented")
    }

//    override fun openMovieDetails(movieId: Int) {
//        val intent = Intent(requireContext(), InfoMovieActivity::class.java)
//        intent.putExtra(MOVIE_ID, movieId)
//        startActivity(intent)
//    }

    override fun loadMoviesWithGenre(categoryIds: List<Int>) {
        moviesViewModel.getMoviesByCategory(categoryIds)
    }

    override fun onWatchListClickedListener(movie: Movie, isChecked: Boolean) {
        if (isChecked) {
            movie.inWatchList = true
            moviesViewModel.addToFavoriteMovie(movie)
//            moviesViewModel.deleteWatchedMovie(movie)
//            MoviesViewModel.writeFavoriteMovie(movie)
        } else {
            movie.inWatchList = false
            moviesViewModel.removeFavoriteMovie(movie)
//            MoviesViewModel.deleteFavoriteMovie(movie)
        }
    }

    override fun onWatchedListClickedListener(movie: Movie, isChecked: Boolean) {
        if (isChecked) {
            movie.watchedMovie = true
            moviesViewModel.removeFavoriteMovie(movie)
//            MoviesViewModel.writeFavoriteMovie(movie)
        } else {
            movie.watchedMovie = false
//            moviesViewModel.deleteWatchedMovie(movie)
//            MoviesViewModel.deleteFavoriteMovie(movie)
        }
    }
}