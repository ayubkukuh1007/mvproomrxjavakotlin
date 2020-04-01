package com.project.mvpkotlincrudroom.Presenter

import androidx.lifecycle.LiveData
import com.project.mvpkotlincrudroom.Room.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserPresenter {

    fun insert(user: User)

    fun update(user: User)

    fun delete(user: User)

    fun deleteAllUsers()

    fun getAllUsers(): LiveData<List<User>>

    fun getUser() : LiveData<User>

    fun getUserbyid(id : Int)

    fun loadUsers();
}