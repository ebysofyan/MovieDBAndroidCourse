package app.ebysofyan.itec.ui.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.ebysofyan.itec.R
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var button: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {
            button = findViewById<MaterialButton>(R.id.click_me)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val queryMap = hashMapOf("page" to "1")
        viewModel.fetchMovies(queryMap).observe(this.viewLifecycleOwner, Observer { value ->
            Log.e("fetchMovies", Gson().toJson(value))
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            doIteration()
        }
    }

    private fun doIteration() {
        // Run iteration on another thread
        GlobalScope.launch {
            repeat(1000000) {
                Log.e("ITERATION", it.toString())
            }
        }
        button.text = "Thanks"
    }
}
