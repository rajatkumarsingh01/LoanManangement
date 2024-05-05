package com.task.loanapplication.domain

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import com.task.loanapplication.data.util.User
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {

    var uploadProgress= mutableStateOf(false)

    private var _selectedLoan = MutableStateFlow("")
    val selectedLoan get() = _selectedLoan

    private var _isLivingOnRent = MutableStateFlow(false)
    val isLivingOnRent get() = _isLivingOnRent

    private val _userData =MutableStateFlow(User())
    val userData get() = _userData

    private val _documentUploadStatus =MutableStateFlow(false)
    val documentUploadStatus get()  = _documentUploadStatus

    fun uploadDocument(documentName: String, uri: Uri?) {
            uploadProgress.value=true
        uri?.let { documentUri ->
            val storage = Firebase.storage
            val storageRef = storage.reference
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userFolderRef = currentUser?.uid?.let { storageRef.child("users").child(it) }
            val documentRef = userFolderRef?.child(documentName)?.child(documentUri.lastPathSegment ?: "")

            documentRef?.putFile(documentUri)
                ?.addOnSuccessListener {
                    // Document uploaded successfully
                    _documentUploadStatus.value = true
                    uploadProgress.value=false
                    Log.d("upload_documents", "documents uploaded successfully ")
                }
                ?.addOnFailureListener { exception ->
                    // Handle failures
                    _documentUploadStatus.value = false
                    Log.e("upload_documents", "Document upload failed: ${exception.message}")
                }
        }
    }


    // Function to update user data
    fun updateUserData(user: User) {
        _userData.value = user
    }


    fun updateSelectedLoan(loan: String) {
        _selectedLoan.value = loan
    }

    fun updateOnRent(value: Boolean) {
        _isLivingOnRent.value = value
    }

    fun registerUserInfo(user: User) {
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val database = Firebase.database
        val usersRef = database.getReference("users")

        usersRef.child(currentUser).setValue(user)
            .addOnSuccessListener {
                // Data successfully written
                Log.d("Inside_registeration", "data written successfully ")
            }
            .addOnFailureListener {
                // Failed to write data
            }

    }

    fun getUserData() {
        val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val database = Firebase.database
        val usersRef = database.getReference("users").child(currentUser)
        //  val userData = MutableLiveData<User>()

        usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    Log.d("inside_get_userData", "data fetched successfully ")
                    _userData.value = user!!
                } else {
                    Log.e("getUserData", "User data is null")
                    // Handle the case where user data is null, perhaps by setting a default user or showing an error
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("get_UserData", "Error fetching user data: ${error.message}")
            }
        })

    }
}