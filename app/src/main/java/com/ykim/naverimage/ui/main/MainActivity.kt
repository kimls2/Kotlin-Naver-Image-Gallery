package com.ykim.naverimage.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ykim.naverimage.R
import com.ykim.naverimage.data.model.NaverImage
import com.ykim.naverimage.ui.SpaceItemDecoration
import com.ykim.naverimage.ui.detail.DetailActivity
import com.ykim.naverimage.ui.main.list.ImageAdapter
import com.ykim.naverimage.util.SIZE_PER_PAGE
import com.ykim.naverimage.util.getAppComponent
import com.ykim.naverimage.util.setVisibility
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainMvp.View {

    lateinit var mainComponent: MainComponent
    @Inject lateinit var presenter: MainPresenter

    private val imageAdapter: ImageAdapter

    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var canMoreLoading = true
    private var lastSearchText = ""
    private var isLoading = false

    init {
        imageAdapter = ImageAdapter(
                {
                    list, position ->
                    startActivity(DetailActivity.createIntent(this, list as ArrayList<NaverImage>, position))
                }

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSearchInput()
        setRecyclerView()
        mainComponent = getAppComponent().inject(MainModule())
        mainComponent.inject(this)
        presenter.onAttach(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun showLoading(show: Boolean) {
        progress_bar.setVisibility(show)
    }

    override fun showError(message: String?) {
        message.let { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
    }

    override fun showImage(items: MutableList<NaverImage>) {
        isLoading = false
        imageAdapter.addImages(items)
    }

    fun setSearchInput() {
        search_btn.setOnClickListener {
            val text = search_input.text.toString().trim()
            if (text != lastSearchText) {
                isLoading = true
                lastSearchText = text
                imageAdapter.clearItems()
                presenter.loadImage(text, 1)
                hideKeyboard(search_input)
            }
        }
    }

    fun setRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = imageAdapter
        recycler_view.addItemDecoration(SpaceItemDecoration(16))
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                var firstVisibleItems: IntArray? = null
                firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    pastVisibleItems = firstVisibleItems[0]
                }

                if (canMoreLoading && !isLoading) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        isLoading = true
                        nextPage()
                    }
                }
            }
        })
    }

    private fun nextPage() {
        isLoading = true
        presenter.loadImage(lastSearchText,
                Math.floor(imageAdapter.itemCount * 1.0 / SIZE_PER_PAGE).toInt() + 1)
    }

    private fun hideKeyboard(focusView: View?) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        focusView ?: currentFocus.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
