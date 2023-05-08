package com.esoapps.bankingapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.esoapps.bankingapp.data.User
import com.esoapps.bankingapp.databinding.UserItemRowBinding


class UserAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<User,
        RecyclerView.ViewHolder>(AdapterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        holder as AdapterViewHolder

        holder.bind(
            user = user,
            listener = listener
        )

    }


    class AdapterViewHolder private constructor(private val binding: UserItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            user: User,
            listener: OnItemClickListener,
        ) {
            binding.element = user

            binding.listener = listener


            //REFRESH SCREEN BINDING:
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserItemRowBinding.inflate(layoutInflater, parent, false)
                return AdapterViewHolder(binding)
            }
        }
    }

    class AdapterDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser.id == newUser.id

        }

        override fun areContentsTheSame(oldUser: User, newUser: User): Boolean {
            return oldUser == newUser
        }
    }


    interface OnItemClickListener {

        fun onClick(element: User)

    }

}