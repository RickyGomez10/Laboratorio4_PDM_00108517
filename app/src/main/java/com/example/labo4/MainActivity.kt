package com.example.labo4

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.labo4.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var movieListN: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun initRecyclerView(){
        viewManager = LinearLayoutManager(this);
        movie_list_rv.apply {
            setHasFixedSize(true);
            layoutManager = viewManager;
            adapter = movieAdapter
        }
    }
    fun addMovieToList(movie: Movie){
        movieListN.add(movie)
        movieAdapter.changeList(movieListN)
        Log.d("Number", movieListN.size.toString())
    }

    private inner class FecthMovie : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            if (params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieUrl = NetworkUtils().buildUrl(movieName)

            return try {
                NetworkUtils().getResponseFromHttpUrl(movieUrl)

            }catch (e: IOException){
                ""
            }
        }

        override fun onPostEXecute(movieInfo: String){

        }


    }
}
