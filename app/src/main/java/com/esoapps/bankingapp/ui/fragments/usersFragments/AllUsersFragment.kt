package com.esoapps.bankingapp.ui.fragments.usersFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.esoapps.bankingapp.MyApp
import com.esoapps.bankingapp.data.Transaction
import com.esoapps.bankingapp.data.User
import com.esoapps.bankingapp.databinding.AllUsersFragmentBinding
import com.esoapps.bankingapp.ui.activities.MainViewModel
import com.esoapps.bankingapp.ui.activities.MainViewModelFactory
import com.esoapps.bankingapp.ui.adapters.UserAdapter


class AllUsersFragment : Fragment() {

    private var binding: AllUsersFragmentBinding? = null

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as MyApp).repository)

    }
    private var userAdapter: UserAdapter? = null

    private var fromStart: Boolean? = null
    private var currentUser: User? = null
    private var amount: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AllUsersFragmentBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllDbUsers()

        //CHECK DIRECTION
        fromStart = AllUsersFragmentArgs.fromBundle(requireArguments()).fromStart

        if (fromStart == false) {
            currentUser = AllUsersFragmentArgs.fromBundle(requireArguments()).currentUser
            amount = AllUsersFragmentArgs.fromBundle(requireArguments()).amount


        }



        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        setAllViewModelObservers()

        setRecyclerView()

        if (fromStart == false && currentUser != null) {
            binding?.toTv?.visibility = View.VISIBLE
        } else {
            binding?.toTv?.visibility = View.INVISIBLE
        }

        val action = AllUsersFragmentDirections.moveStartFrag()

        handleBackPressed(action = action)


    }


    private fun setAllViewModelObservers() {


        viewModel.allDbUsers.observe(viewLifecycleOwner) {

            if (it != null) {
                if (it.isNotEmpty()) {

                    val listOfUser = it.toMutableList()

                    //REMOVE THE CURRENT USER FROM DB LIST
                    if (fromStart == false && currentUser != null) {

                        listOfUser.remove(currentUser)
                    }


                    userAdapter?.submitList(listOfUser)

                }


            }
        }


    }

    private fun setRecyclerView() {
        userAdapter = UserAdapter(
            object : UserAdapter.OnItemClickListener {
                override fun onClick(element: User) {
                    if (fromStart == false
                        &&
                        currentUser != null
                        &&
                        amount != null
                    ) {


                        val transaction = Transaction(
                            fromUser = currentUser!!,
                            toUser = element,
                            amount = amount!!
                        )
                        viewModel.addTransaction(transaction = transaction)

                        currentUser!!.currentBalance = currentUser!!.currentBalance - amount!!
                        element.currentBalance = element.currentBalance + amount!!

                        viewModel.updateUser(user = currentUser!!)
                        viewModel.updateUser(user = element)


                        Toast.makeText(
                            context,
                            "Transaction Done", Toast.LENGTH_LONG
                        ).show()

                        //findNavController().navigateUp()

                        val action = AllUsersFragmentDirections.moveStartFrag()
                        navigateDirection(action)

                    } else {
                        //START TRANSACTION SCREENS NORMALLY
                        val action = AllUsersFragmentDirections.userDetailsFrag(element)
                        navigateDirection(action)
                    }

                }


            })

        binding?.rv?.adapter = userAdapter

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
