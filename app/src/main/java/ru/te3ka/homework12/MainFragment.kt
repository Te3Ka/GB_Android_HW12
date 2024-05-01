package ru.te3ka.homework12

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.te3ka.homework12.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener {
            val searchText = binding.editTextSearch.text.toString()
            viewModel.onButtonSearchClick(searchText)
        }

        binding.editTextSearch.addTextChangedListener {
            val searchText = it.toString()
            binding.buttonSearch.isEnabled = searchText.length >= 3
        }

        viewModel.isSearching.observe(viewLifecycleOwner, Observer { isSearching ->
            if (isSearching) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                if (viewModel.searchResult.value == null) {
                    binding.textSearch.text = "По запросу " +
                            "${binding.editTextSearch.text.toString()}" +
                            " ничего не найдено"
                }
            }
        })

        return binding.root
    }
}