package com.tps.challenge.features.storefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tps.challenge.R
import com.tps.challenge.TCApplication
import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel
import javax.inject.Inject

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class StoreFeedFragment : Fragment(), StoreFeedContract.View {
    companion object {
        const val TAG = "StoreFeedFragment"
    }
    private lateinit var storeFeedAdapter: StoreFeedAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    @Inject
    lateinit var presenter : StoreFeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TCApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        presenter.onViewLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_feed, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        // Enable if Swipe-To-Refresh functionality will be needed
        swipeRefreshLayout.isEnabled = false

        recyclerView = view.findViewById<RecyclerView>(R.id.stores_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

    override fun onDataLoaded(data: List<StoreFeedAdapterItemModel>) {
        storeFeedAdapter = StoreFeedAdapter(data)
        recyclerView.apply {
            adapter = storeFeedAdapter
        }
    }
}
