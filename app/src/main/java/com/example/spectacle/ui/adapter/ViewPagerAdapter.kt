package com.example.spectacle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.spectacle.ui.fragments.FavoriteMoviesFragment
import com.example.spectacle.ui.fragments.MoviesFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ALL_MOVIES_POSITION -> MoviesFragment()
            FAVORITE_MOVIES_POSITION -> FavoriteMoviesFragment()
            else -> MoviesFragment()
        }
    }

    companion object {
        const val ALL_MOVIES_POSITION = 0
        const val FAVORITE_MOVIES_POSITION = 1
    }
}