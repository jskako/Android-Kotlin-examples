package com.gamingingrs.roomwithhilt_example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamingingrs.roomwithhilt_example.data.model.AnimalFamily.CAT
import com.gamingingrs.roomwithhilt_example.data.model.Animals
import com.gamingingrs.roomwithhilt_example.data.repository.AnimalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository
) : ViewModel() {

    val animals = animalsRepository.getAll() // Get observer
    val animalFamilyCat =
        animalsRepository.getAnimalsBy(CAT.name) // Get observer for specific animal family

    fun addAnimal(animal: String, family: String) {
        if (animal.isNotEmpty() && family.isNotEmpty()) {
            viewModelScope.launch(Main) {
                animalsRepository.insert(Animals(animal, family))
            }
        }
    }

}