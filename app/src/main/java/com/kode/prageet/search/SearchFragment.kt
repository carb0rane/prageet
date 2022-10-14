package com.kode.prageet.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kode.prageet.AppStatus.AppStatusViewModel
import com.kode.prageet.R
import com.kode.prageet.databinding.FragmentSearchBinding
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlin.math.log

private const val TAG = "SearchFragment"

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var sFlow: Flow<String>
    private lateinit var searchViewModel: SearchViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        val appStatusViewModel = ViewModelProvider(requireActivity())[AppStatusViewModel::class.java]
        searchViewModel.context = requireActivity()
        CoroutineScope(Dispatchers.IO).launch {
            searchViewModel.getTextQuery(binding.etSearch.testsdf())

        }

        binding.rcViewSearch.adapter = SearchRecycler(searchViewModel.songList ,appStatusViewModel )
        binding.rcViewSearch.layoutManager = LinearLayoutManager(context)

        searchViewModel.searchResult.observe(viewLifecycleOwner) {
            binding.rcViewSearch.adapter?.notifyDataSetChanged()

        }
    }

    fun EditText.testsdf(): Flow<CharSequence?> {
        return callbackFlow<CharSequence?> {
            val listener = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Log.d(TAG, "onTextChanged: $s")
                    trySend(s)
                }

                override fun afterTextChanged(s: Editable?) {}

            }

            addTextChangedListener(listener)
            awaitClose {
                removeTextChangedListener(listener)
            }

        }//.onStart { emit(text) }

    }


}