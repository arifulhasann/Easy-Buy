package com.example.easybuy.views.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.easybuy.R
import com.example.easybuy.base.BaseFragment
import com.example.easybuy.core.DataState
import com.example.easybuy.databinding.FragmentLoginBinding
import com.example.easybuy.isEmpty
import com.example.easybuy.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel : LoginViewModel by viewModels()
    private fun loginObserver() {
       viewModel.loginResponse.observe(viewLifecycleOwner){
           when(it){
               is DataState.Error -> {
                   loading.dismiss()
                   Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
               }
               is DataState.Loading -> {
                   loading.show()
               //Toast.makeText(context,"Loading...",Toast.LENGTH_LONG).show()
               }
               is DataState.Success -> {
                   loading.dismiss()
                   Toast.makeText(context,"Login sucess",Toast.LENGTH_LONG).show()
                   startActivity(Intent(requireContext(), SellerDashboard::class.java))
               }
           }
       }
    }

   override fun setListener() {
        with(binding){
            btnregister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if(!etEmail.isEmpty() && !etPassword.isEmpty()){
                 //   Toast.makeText(context,"All Input Done !",Toast.LENGTH_LONG).show()
                    val user = UserLogin(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                    viewModel.userLogin(user)

                }
            }
        }
    }

    override fun allObserver() {
        loginObserver()
    }

}