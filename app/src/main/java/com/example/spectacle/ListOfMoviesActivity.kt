package com.example.spectacle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.spectacle.databinding.ActivityLisfOfMoviesBinding
import com.example.spectacle.ui.adapter.ViewPagerAdapter
import com.example.spectacle.ui.adapter.ViewPagerAdapter.Companion.ALL_MOVIES_POSITION
import com.example.spectacle.ui.adapter.ViewPagerAdapter.Companion.FAVORITE_MOVIES_POSITION
import com.example.spectacle.ui.login.FormLogin
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class ListOfMoviesActivity : AppCompatActivity() {

    private var searchEdtTxt: EditText? = null
    private lateinit var searchBtn: ImageButton
    private lateinit var greenIcon: ImageView
    private lateinit var searchModeTxt: TextView
    private lateinit var backToHomeBtn: TextView
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentContainer: FrameLayout
//    private lateinit var movieSearched: String
//    private var searchFragment: SearchMoviesFragment? = null

    private lateinit var binding: ActivityLisfOfMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLisfOfMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
        logout()

        supportActionBar!!.hide()
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            ALL_MOVIES_POSITION -> "Meus Filmes"
            FAVORITE_MOVIES_POSITION -> "Filmes"
            else -> ""
        }
    }
//
//    private fun visibilitySearchMode() {
//        tbLytOptions.visibility = View.GONE
//        viewPager.visibility = View.GONE
//        greenIcon.visibility = View.VISIBLE
//        searchModeTxt.visibility = View.VISIBLE
//        backToHomeBtn.visibility = View.VISIBLE
//        fragmentContainer.visibility = View.VISIBLE
//    }

    private fun visibilityNotSearchMode() {
        tbLytOptions.visibility = View.VISIBLE
        viewPager.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
        greenIcon.visibility = View.GONE
        searchModeTxt.visibility = View.GONE
        backToHomeBtn.visibility = View.GONE
    }

    private fun bindViews() {
//        searchEdtTxt = findViewById(R.id.searchMovie)
        searchBtn = findViewById(R.id.submitSearch)
        tbLytOptions = findViewById(R.id.tabLytOptions)
        viewPager = findViewById(R.id.viewPager)
        greenIcon = findViewById(R.id.greenIcon)
        searchModeTxt = findViewById(R.id.searchModeTxt)
        backToHomeBtn = findViewById(R.id.backToHomeBtn)
        fragmentContainer = findViewById(R.id.searchFragmentContainer)

        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
        TabLayoutMediator(tbLytOptions, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

//        searchEdtTxt?.setOnEditorActionListener { _, actionId, _ ->
//            when (actionId) {
//                EditorInfo.IME_ACTION_SEARCH -> {
//                    movieSearched = searchEdtTxt?.text.toString()
//                    if (searchFragment == null) {
//                        searchFragment = SearchMoviesFragment.newInstance(movieSearched)
//                        searchFragment?.let {
//                            supportFragmentManager.beginTransaction()
//                                .replace(R.id.searchFragmentContainer, it)
//                                // .addToBackStack(null)
//                                .commit()
//                        }
//                    } else {
//                        searchFragment?.updateQuery(movieSearched.toUri())
//                    }
//                    true
//                }
//                else -> false
//            }
//        }
//
//        searchEdtTxt?.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                visibilitySearchMode()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                if (s != null) {
//                    if (s.isEmpty()) {
//                        viewPager.setCurrentItem(ALL_MOVIES_POSITION, false)
//                        visibilityNotSearchMode()
//                    }
//                }
//            }
//        })

        backToHomeBtn.setOnClickListener {
            visibilityNotSearchMode()
            searchEdtTxt?.text?.clear()
        }
    }

    private fun logout() {
        binding.logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            backScreenLogin()
        }
    }

    private fun backScreenLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()

        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest(
                AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE,
                {
                    AccessToken.setCurrentAccessToken(null)
                    LoginManager.getInstance().logOut()

                    finish()
                }
            ).executeAsync()
        }
    }

//    companion object {
//        const val LIST_ID = "LIST_ID"
//    }
}