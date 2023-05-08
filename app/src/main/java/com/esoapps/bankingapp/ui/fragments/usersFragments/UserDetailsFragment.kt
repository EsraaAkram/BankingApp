package com.esoapps.bankingapp.ui.fragments.usersFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.esoapps.bankingapp.MyApp
import com.esoapps.bankingapp.data.User
import com.esoapps.bankingapp.databinding.UserDetailsFragmentBinding
import com.esoapps.bankingapp.ui.activities.MainViewModel
import com.esoapps.bankingapp.ui.activities.MainViewModelFactory


class UserDetailsFragment : Fragment() {

    private var binding: UserDetailsFragmentBinding? = null

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as MyApp).repository)

    }
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = UserDetailsFragmentBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser = UserDetailsFragmentArgs.fromBundle(requireArguments()).currentUser

        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.element = currentUser

        btnsClicks()

        val action = UserDetailsFragmentDirections.moveStartFrag()
        handleBackPressed(action = action)

    }


    private fun btnsClicks() {

        binding?.transferBtn?.setOnClickListener {

            if (binding?.amountEdt?.text.toString().isNotEmpty()) {
                val amount = binding?.amountEdt?.text.toString().toIntOrNull()
                if (amount != null && currentUser != null) {


                    moveBalance(amount = amount)
                } else {
                    Toast.makeText(
                        context,
                        "Enter the Right amount...", Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    }

    private fun moveBalance(amount: Int) {

        if (currentUser != null) {
            if (amount < currentUser!!.currentBalance) {
                val action = UserDetailsFragmentDirections.allUsersFrag(
                    fromStart = false,
                    amount,
                    currentUser
                )




                navigateDirection(action = action)
            } else {
                Toast.makeText(
                    context,
                    "Enter the Right amount...", Toast.LENGTH_LONG
                ).show()
            }
        }


    }

    private fun handleBackPressed(action: NavDirections) {

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {


                    navigateDirection(action)

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
    }


    private fun navigateDirection(action: NavDirections) {
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}