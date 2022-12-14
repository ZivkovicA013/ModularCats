package com.zia1bg.modularcats.feature.fact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zia1bg.modularcats.feature.fact.databinding.FragmentFactBinding
import dagger.hilt.android.AndroidEntryPoint


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

        this.viewModel.getObservableFact().observe(viewLifecycleOwner) {
            binding.tvCatFact.text = it
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