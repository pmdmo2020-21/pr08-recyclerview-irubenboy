package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityItemBinding

class UsersAdapter: ListAdapter<User, UsersAdapter.ViewHolder>(UsersDiffCallBack) {

    object UsersDiffCallBack: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem.name == newItem.name && oldItem.email == newItem.email
                && oldItem.phoneNumber == newItem.phoneNumber && oldItem.address == newItem.address && oldItem.photoUrl == newItem.photoUrl
                && oldItem.web == newItem.web


    }

    class ViewHolder(private val binding: UsersActivityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            user.run {
                binding.lblName.text = name
                binding.lblEmail.text = email
                binding.lblTel.text = phoneNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UsersActivityItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}