package com.zia1bg.modularcats.feature.fact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zia1bg.modularcats.feature.fact.databinding.FragmentFactBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FactFragment : Fragment() {

    private var _binding: FragmentFactBinding? = null
    // This property is only valid between onCreateView and onDestroyView

    private val viewModel: FactViewModel by viewModels()

    private val binding get() = _binding!!
    //!! operator is not null assertion operator - converts nullable var in non-nullable
    // binding is only valid between onCreateView and onDestroyView
    // prevents memory leak when view is destroyed


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFactBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.catFactState.collect { catFactState ->

                    binding.progressBar.visibility.apply {
                        if (catFactState.isLoading)
                            binding.progressBar.visibility = View.VISIBLE
                        else
                            binding.progressBar.visibility = View.GONE
                    }

                    binding.tvCatFact.text = catFactState.fact?.text ?: ""
                }

            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGenerateFact.setOnClickListener {
            viewModel.getCatFact()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}