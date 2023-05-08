package com.esoapps.bankingapp.ui.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.esoapps.bankingapp.databinding.StartFragmentBinding


class StartFragment : Fragment() {

    private var binding: StartFragmentBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = StartFragmentBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = this


        btnsClicks()
    }

    private fun btnsClicks() {

        binding?.allUsersBtn?.setOnClickListener {

            val action = StartFragmentDirections.moveAllUsers(
                fromStart = true,
                0,
                null
            )
            navigateDirection(action = action)
        }
        binding?.allTransactionsBtn?.setOnClickListener {
            val action = StartFragmentDirections.moveToAllTransactions()
            navigateDirection(action = action)
        }
    }

    private fun navigateDirection(action: NavDirections) {
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}