package com.project.mvpkotlincrudroom.ui.Readuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.mvpkotlincrudroom.R
import com.project.mvpkotlincrudroom.Room.User
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val users: List<User>, val onclickuser: Onclickuser) : RecyclerView.Adapter<ListAdapter.UserHolder>() {

    private var mUser : List<User>

    init {
      mUser = users
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): UserHolder {
        return UserHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener { view ->  onclickuser?.detailuser(users[position])  }
    }

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mNamaView = view.pdnama
        private var mProfesiView = view.profesi
        private var mNoView = view.nohp
        private var mDetailView = view.detail
        private var mDeleteView = view.hapus

        init {
            mDetailView.setOnClickListener { view -> onclickuser?.detailuser(mUser.get(adapterPosition))  }
            mDeleteView.setOnClickListener { view -> onclickuser?.hapususer(mUser.get(adapterPosition))  }
        }

        fun bind(user: User) {
            mNamaView?.text = user.namadepan + user.namabelakang
            mProfesiView?.text = user.profesi
            mNoView?.text = user.nohp
        }

    }

    interface Onclickuser {
        fun detailuser(user: User)
        fun hapususer(user: User)
    }
}



