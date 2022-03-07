package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelClasses.Article
import com.example.myapplication.Repository.NewsRepository
import com.example.recyclerview.MyAdapter
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.MainViewModelFactory
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.NewsRetrofitHelper
import com.yogify.kotlinprojectjetpack.Architecture_Component.MvvmWithRetrofit_NewsApp.API.NewsService

lateinit var mainViewModel: MainViewModel
lateinit var adapter: MyAdapter
lateinit var newsList: RecyclerView
private var articles = mutableListOf<Article>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Retrofit Helper
      //  val newsService = NewsRetrofitHelper.getInstance().create(NewsService::class.java)

        val repository = (application as ApplicationClass).newsRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


        newsList = findViewById<RecyclerView>(R.id.newsList)
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        mainViewModel.newsarticles.observe(this, androidx.lifecycle.Observer {
            Log.d("newsIn", it.articles.toString())
            articles = it.articles.toList().toMutableList()
            adapter = MyAdapter(this@MainActivity, articles)
            newsList.adapter = adapter


        })


    }
//    val news  = response.body()
//    if (news != null)
//    Log.d("aditya", news.toString())
//    if (news != null) {
//        articles.addAll(news.articles)
//    }
//    adapter.notifyDataSetChanged()

}

