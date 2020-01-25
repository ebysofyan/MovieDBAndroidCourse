package app.ebysofyan.itec.ui.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ebysofyan.itec.R
import app.ebysofyan.itec.core.base.ApiResponse
import app.ebysofyan.itec.core.base.PaginateResponse
import app.ebysofyan.itec.data.remote.model.MovieModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var isLoading: Boolean = false
    private var currentPage: Int = 1
    private var totalPage: Long = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRecyclerView()
        fetchMovies(currentPage.toString())
    }

    private fun fetchMovies(page: String) {
        val queryMap = hashMapOf("page" to page)
        viewModel.fetchMovies2(queryMap).observe(this.viewLifecycleOwner, Observer { response ->
            onMovieFetched(response)
        })
    }

    private lateinit var adapter: MainRecyclerViewAdapter
    private fun initRecyclerView() {
        adapter = MainRecyclerViewAdapter()
        main_recycler_view.layoutManager = GridLayoutManager(context, 3)
        main_recycler_view.adapter = adapter

        main_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager

                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItems().size - 1 && currentPage.toLong() != totalPage) {
                        isLoading = true
                        showItemLoading()

                        currentPage += 1
                        fetchMovies(currentPage.toString())
                    }
                }
            }
        })

        showItemLoading()
    }

    private fun onMovieFetched(response: ApiResponse<PaginateResponse<MovieModel>?>?) {
        when (response) {
            is ApiResponse.Success -> {
                isLoading = false
                totalPage = response.data?.totalPages ?: 1
                adapter.getItems().removeAt(adapter.getItems().size - 1)
                adapter.notifyItemRemoved(adapter.getItems().size)

                if (response.data?.page == 1) adapter.clear()
                response.data?.results?.let {
                    adapter.addItems(it.toMutableList())
                }
            }
            is ApiResponse.Failure -> Toast.makeText(
                context,
                response.detail,
                Toast.LENGTH_LONG
            ).show()
            is ApiResponse.Error -> Toast.makeText(
                context,
                response.e.message.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun showItemLoading() {
        adapter.addItem(null)
        adapter.notifyItemInserted(adapter.getItems().size - 1)
    }
}
