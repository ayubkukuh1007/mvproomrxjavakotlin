package com.project.mvpkotlincrudroom.ui.Readuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mvpkotlincrudroom.Interactors.IUserInteractor
import com.project.mvpkotlincrudroom.Interactors.UserInteractor
import com.project.mvpkotlincrudroom.Presenter.IUserPresenter
import com.project.mvpkotlincrudroom.Presenter.UserPresenter
import com.project.mvpkotlincrudroom.R
import com.project.mvpkotlincrudroom.Repository.IUserRepository
import com.project.mvpkotlincrudroom.Repository.UserRepository
import com.project.mvpkotlincrudroom.Room.User
import com.project.mvpkotlincrudroom.Room.UserRoomDatabase
import com.project.mvpkotlincrudroom.ui.Readuser.adapter.ListAdapter
import com.project.mvpkotlincrudroom.ui.Detailuser.DetailUser
import kotlinx.android.synthetic.main.activity_read_user.*

class ReadUser : AppCompatActivity(),ListAdapter.Onclickuser {

    lateinit var userPresenter: IUserPresenter
    lateinit var iUserRepository : IUserRepository
    lateinit var iUserInteractor : IUserInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_user)

        val userDao = UserRoomDatabase.getDatabase(this).userDao();
        iUserRepository = UserRepository(userDao)
        iUserInteractor = UserInteractor(iUserRepository as UserRepository)
        userPresenter = UserPresenter(iUserInteractor)

        userPresenter.getAllUsers().observe(this, Observer<List<User>> { users ->
            rv_user.apply {
                layoutManager = LinearLayoutManager(this@ReadUser)
                adapter =
                    ListAdapter(
                        users,this@ReadUser
                    )
            }
        })
    }

    override fun detailuser(user: User) {
        val intent = Intent(this, DetailUser::class.java)
        intent.putExtra(ID_USER,user.id)
        startActivity(intent)
    }

    override fun hapususer(user: User) {
        userPresenter.delete(user)
    }

    companion object {
        const val ID_USER = "ID_USER"
    }
}
