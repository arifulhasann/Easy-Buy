package com.example.easybuy.views.register

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.easybuy.R
import com.example.easybuy.base.BaseFragment
import com.example.easybuy.core.DataState
import com.example.easybuy.databinding.FragmentRegisterBinding
import com.example.easybuy.isEmpty
import com.example.easybuy.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel : RegistrationViewModel by viewModels()

    private fun registrationObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loading.show()
                   // Toast.makeText(context,"Loading...",Toast.LENGTH_LONG).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context,"Account Create Success",Toast.LENGTH_LONG).show()
                    startActivity(Intent(requireContext(), SellerDashboard::class.java))

                }
            }
        }
    }
    override fun setListener() {
       with(binding){
           btnLogin.setOnClickListener{
               findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

           }
           btnregister.setOnClickListener{
               etEmail.isEmpty()
               etName.isEmpty()
               etPassword.isEmpty()
              if(!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()){
                //  Toast.makeText(context,"All input done !",Toast.LENGTH_LONG).show()

                  val user = UserRegister(
                      etName.text.toString(),
                      etEmail.text.toString(),
                      etPassword.text.toString(),
                      "Seller",
                      ""
                  )
                  viewModel.userRegistration(user)
              }
           }
       }
    }

    override fun allObserver() {
        registrationObserver()
    }
}