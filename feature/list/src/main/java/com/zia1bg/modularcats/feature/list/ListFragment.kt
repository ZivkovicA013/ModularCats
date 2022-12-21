package com.zia1bg.modularcats.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zia1bg.modularcats.feature.list.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and onDestroyView

    private val binding get() = _binding!!
    //!! operator is not null assertion operator - converts nullable var in non-nullable
    // binding is only valid between onCreateView and onDestroyView
    // prevents memory leak when view is destroyed

    lateinit var breedListAdapter: BreedListAdapter

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.breedUiState.collect { breedsUiState ->

                    binding.progressBar.visibility.apply {
                        if (breedsUiState.isLoading)
                            binding.progressBar.visibility = View.VISIBLE
                        else
                            binding.progressBar.visibility = View.GONE
                    }
                    //NPE if repository fail to fetch list of breeds
                    //TODO add mechanism for error if list is null
                    breedListAdapter.submitList(breedsUiState.breeds)
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRV()
    }

//    private fun setUpObservers() {
//        viewmodel.breed.observe(viewLifecycleOwner) {
//            breedListAdapter.submitList(it)
//        }
//
//        viewmodel.loading.observe(viewLifecycleOwner) {
//            binding.progressBar.visibility.apply {
//                if (it)
//                    binding.progressBar.visibility = View.VISIBLE
//                else binding.progressBar.visibility = View.GONE
//            }
//
//        }
//    }

    private fun setUpRV() = binding.rvBreedList.apply {
        breedListAdapter = BreedListAdapter()
        adapter = breedListAdapter
        layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
        setHasFixedSize(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}