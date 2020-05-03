package com.home.kotlincoroutinesdemo.albums.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.home.kotlincoroutinesdemo.R
import com.home.kotlincoroutinesdemo.albums.model.local.AlbumsEntity
import com.home.kotlincoroutinesdemo.albums.model.local.DatabaseAlbumsHelperImpl
import com.home.kotlincoroutinesdemo.albums.model.remote.ApiAlbumsHelperImpl
import com.home.kotlincoroutinesdemo.albums.viewmodel.AlbumsViewModel
import com.home.kotlincoroutinesdemo.common.ViewModelFactory
import com.home.kotlincoroutinesdemo.common.model.Status
import com.home.kotlincoroutinesdemo.common.model.local.DatabaseBuilder
import com.home.kotlincoroutinesdemo.common.model.remote.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_albums.*

class AlbumsActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumsViewModel
    private lateinit var adapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        adapter = AlbumsAdapter(arrayListOf())
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.context,
                (recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        recycler_view.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiAlbumsHelperImpl(RetrofitBuilder.apiAlbumsService),
                DatabaseAlbumsHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(AlbumsViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getAlbums().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recycler_view.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<AlbumsEntity>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}
