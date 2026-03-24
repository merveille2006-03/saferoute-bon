package com.saferoute.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saferoute.domain.model.EmergencyContact
import com.saferoute.domain.repository.EmergencyContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactRepository: EmergencyContactRepository
) : ViewModel() {

    private val userId = "default_user"

    private val _contacts = MutableStateFlow<List<EmergencyContact>>(emptyList())
    val contacts: StateFlow<List<EmergencyContact>> = _contacts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                contactRepository.getAllContactsFlow(userId).collect { list ->
                    _contacts.value = list.sortedBy { it.priority }
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to load contacts")
                _errorMessage.value = "Impossible de charger les contacts"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addContact(
        name: String,
        phoneNumber: String,
        email: String?,
        relationship: String
    ) {
        viewModelScope.launch {
            try {
                val contact = EmergencyContact(
                    userId = userId,
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    relationship = relationship,
                    priority = _contacts.value.size + 1
                )
                contactRepository.addContact(contact)
            } catch (e: Exception) {
                Timber.e(e, "Failed to add contact")
                _errorMessage.value = "Impossible d'ajouter le contact"
            }
        }
    }

    fun updateContact(contact: EmergencyContact) {
        viewModelScope.launch {
            try {
                contactRepository.updateContact(contact)
            } catch (e: Exception) {
                Timber.e(e, "Failed to update contact")
                _errorMessage.value = "Impossible de modifier le contact"
            }
        }
    }

    fun deleteContact(contact: EmergencyContact) {
        viewModelScope.launch {
            try {
                contactRepository.deleteContact(contact)
            } catch (e: Exception) {
                Timber.e(e, "Failed to delete contact")
                _errorMessage.value = "Impossible de supprimer le contact"
            }
        }
    }

    fun toggleContactEnabled(contact: EmergencyContact) {
        viewModelScope.launch {
            try {
                contactRepository.updateContact(contact.copy(isEnabled = !contact.isEnabled))
            } catch (e: Exception) {
                Timber.e(e, "Failed to toggle contact")
            }
        }
    }

    fun reorderContacts(fromIndex: Int, toIndex: Int) {
        viewModelScope.launch {
            try {
                val mutableList = _contacts.value.toMutableList()
                val movedContact = mutableList.removeAt(fromIndex)
                mutableList.add(toIndex, movedContact)

                // Update priorities
                mutableList.forEachIndexed { index, contact ->
                    contactRepository.updateContact(contact.copy(priority = index + 1))
                }
            } catch (e: Exception) {
                Timber.e(e, "Failed to reorder contacts")
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}