package com.gamingingrs.roomwithhilt_example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gamingingrs.roomwithhilt_example.data.model.AnimalFamily
import com.gamingingrs.roomwithhilt_example.databinding.FragmentDataBinding
import com.gamingingrs.roomwithhilt_example.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)

        binding.addToDatabaseButton.setOnClickListener {
            val animal = binding.animalEditText.text.toString()
            val family = binding.familyTypeLayout.editText?.text.toString()
            if (animal.isNotEmpty() && family.isNotEmpty()) {
                viewModel.addAnimal(animal, family)
                binding.animalEditText.setText("")
                binding.familyTypeLayout.editText?.setText("")
                "Animal $animal from family $family added to database.".toast(requireContext())

            } else {
                "Something went wrong.".toast(requireContext())
            }
        }

        setupFamilyAdapter()

        viewModel.animals.observe(viewLifecycleOwner) { animal ->
            "Animal: ${animal.last().animal}\nFamily: ${animal.last().family}\n\n".also {
                binding.result.text = it
            }
        }

        viewModel.animalFamilyCat.observe(viewLifecycleOwner) { animal ->
            "Animal: ${animal.last().animal}\nFamily: ${animal.last().family}\n\n".also {
                binding.resultCat.text = it
            }
        }

        return binding.root
    }

    private fun setupFamilyAdapter() {
        val familyList = AnimalFamily.values()
        val adapterType: ArrayAdapter<AnimalFamily?> = ArrayAdapter<AnimalFamily?>(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            familyList
        )

        binding.familyType.setAdapter(adapterType)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}