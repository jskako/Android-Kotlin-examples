package com.gamingingrs.roomwithhilt_example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamingingrs.roomwithhilt_example.database.model.AnimalFamily.CAT
import com.gamingingrs.roomwithhilt_example.database.model.Animals
import com.gamingingrs.roomwithhilt_example.database.repository.AnimalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val animalsRepository: AnimalsRepository
) : ViewModel() {

    val animals = animalsRepository.getAll() // Get observer
    val animalFamily =
        animalsRepository.getAnimalsBy(CAT.name) // Get observer for specific animal family

    fun addAnimal(animal: String, family: String) {
        if(animal.isNotEmpty() && family.isNotEmpty()) {
            viewModelScope.launch(Main) {
                animalsRepository.insert(Animals(animal, family))
            }
        }
    }

}