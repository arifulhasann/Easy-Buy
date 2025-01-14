package com.example.easybuy.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easybuy.core.DataState
import com.example.easybuy.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthRepository):ViewModel() {
    private val _loginResponse = MutableLiveData<DataState<UserLogin>>()
    val loginResponse : LiveData<DataState<UserLogin>> = _loginResponse

    fun userLogin(user: UserLogin) {
        _loginResponse.postValue(DataState.Loading())

        authService.userLogin(user).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                _loginResponse.postValue(DataState.Success(user))

                //Log.d("TAG", "userLogin: Login Success")

            } else {
                _loginResponse.postValue(DataState.Error("${task.exception?.message}"))

               // Log.d("TAG", "userLogin: ${task.exception?.message}")
                //Log.d("TAG", "userLogin: Login Failed")
            }

        }
    }
}
