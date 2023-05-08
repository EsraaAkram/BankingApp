package com.esoapps.bankingapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esoapps.bankingapp.data.Transaction
import com.esoapps.bankingapp.databinding.TransactionItemRowBinding


class TransactionAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<Transaction,
        RecyclerView.ViewHolder>(AdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val transaction = getItem(position)
        holder as AdapterViewHolder

        holder.bind(
            transaction = transaction,
            listener = listener
        )

    }


    class AdapterViewHolder private constructor(private val binding: TransactionItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            transaction: Transaction,
            listener: OnItemClickListener,
        ) {
            binding.element = transaction

            binding.listener = listener


            //REFRESH SCREEN BINDING:
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransactionItemRowBinding.inflate(layoutInflater, parent, false)
                return AdapterViewHolder(binding)
            }
        }
    }

    class AdapterDiffUtil : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldElement: Transaction, newElement: Transaction): Boolean {
            return oldElement.id == newElement.id

        }

        override fun areContentsTheSame(oldElement: Transaction, newElement: Transaction): Boolean {
            return oldElement == newElement
        }
    }


    interface OnItemClickListener {

        fun onClick(element: Transaction)

    }

}