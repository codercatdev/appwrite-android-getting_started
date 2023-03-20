package com.appwrite.gettingstarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.appwrite.Client
import io.appwrite.services.Locale
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

  private var columnCount = 1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      columnCount = it.getInt(ARG_COLUMN_COUNT)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_item_list, container, false)

    // Set the adapter
    if (view is RecyclerView) {
      with(view) {

        val client = Client(view.context)
          .setEndpoint("https://demo.appwrite.io/v1")
          .setProject("64134dee020fe37f1bfe")
        val locale = Locale(client)

        layoutManager = when {
          columnCount <= 1 -> LinearLayoutManager(context)
          else -> GridLayoutManager(context, columnCount)
        }
        runBlocking {
          launch {
            val countries = locale.listCountries().countries
            adapter = MyItemRecyclerViewAdapter(countries)

          }
        }
      }
    }
    return view
  }

  companion object {

    // TODO: Customize parameter argument names
    const val ARG_COLUMN_COUNT = "column-count"

    // TODO: Customize parameter initialization
    @JvmStatic
    fun newInstance(columnCount: Int) =
      ItemFragment().apply {
        arguments = Bundle().apply {
          putInt(ARG_COLUMN_COUNT, columnCount)
        }
      }
  }
}
