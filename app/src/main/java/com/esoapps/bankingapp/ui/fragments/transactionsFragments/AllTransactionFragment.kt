package com.esoapps.bankingapp.ui.fragments.transactionsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.esoapps.bankingapp.MyApp
import com.esoapps.bankingapp.data.Transaction
import com.esoapps.bankingapp.databinding.AllTransactionsFragmentBinding
import com.esoapps.bankingapp.ui.activities.MainViewModel
import com.esoapps.bankingapp.ui.activities.MainViewModelFactory
import com.esoapps.bankingapp.ui.adapters.TransactionAdapter

class AllTransactionFragment : Fragment() {

    private var binding: AllTransactionsFragmentBinding? = null

    private var transactionAdapter: TransactionAdapter? = null

    private val viewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((activity?.application as MyApp).repository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllTransactionsFragmentBinding.inflate(inflater)

        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllDbTransactions()




        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        setAllViewModelObservers()

        setRecyclerView()


    }

    private fun setAllViewModelObservers() {


        viewModel.allDbTransactions.observe(viewLifecycleOwner) {


            if (it != null) {
                if (it.isNotEmpty()) {

                    binding?.noTransactionsTv?.visibility = View.INVISIBLE
                    binding?.rv?.visibility = View.VISIBLE
                    transactionAdapter?.submitList(it)

                }


            } else {
                binding?.noTransactionsTv?.visibility = View.VISIBLE
                binding?.rv?.visibility = View.INVISIBLE
            }
        }


    }

    private fun setRecyclerView() {
        transactionAdapter = TransactionAdapter(
            object : TransactionAdapter.OnItemClickListener {
                override fun onClick(element: Transaction) {


                }


            })

        binding?.rv?.adapter = transactionAdapter

    }


}