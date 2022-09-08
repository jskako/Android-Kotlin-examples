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
import com.gamingingrs.roomwithhilt_example.getNames
import com.gamingingrs.roomwithhilt_example.toast


class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)

        setupFamilyAdapter()

        binding.addToDatabaseButton.setOnClickListener {
            val animal = binding.animalEditText.text.toString()
            val family = binding.familyTypeLayout.editText?.text.toString()
            if (animal.isNotEmpty() && family.isNotEmpty()) {
                viewModel.addAnimal(animal, family)
            } else {
                "Something went wrong.".toast(requireContext())
            }
        }


        return binding.root

    }

    private fun setupFamilyAdapter() {
        val familyList = getNames(AnimalFamily::class.java)
        val adapterType: ArrayAdapter<String?> = ArrayAdapter<String?>(
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