package com.project.mvpkotlincrudroom.Presenter

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.mvpkotlincrudroom.Interactors.IUserInteractor
import com.project.mvpkotlincrudroom.Room.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserPresenter (private val iUserInteractor: IUserInteractor) : IUserPresenter {

    private val allUsers : MutableLiveData<List<User>> = MutableLiveData()
    private val user : MutableLiveData<User> = MutableLiveData()

    init {
        loadUsers()
    }

    override fun insert(user: User) {
        iUserInteractor.insert(user)
    }

    override fun update(user: User) {
        iUserInteractor.update(user)
    }

    override fun delete(user: User) {
        iUserInteractor.delete(user)
    }

    override fun deleteAllUsers() {
        iUserInteractor.deleteAllQuotes()
    }

    @SuppressLint("CheckResult")
    override fun loadUsers() {
        iUserInteractor.getAllUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users -> allUsers.postValue(users) },
                { error -> Log.d("RxJava", "Error getting info from interactor into presenter") }
            )
    }

    override fun getUser(): LiveData<User> {
        return user
    }

    @SuppressLint("CheckResult")
    override fun getUserbyid(id: Int) {
        iUserInteractor.getUserbyid(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {singleuser -> user.postValue(singleuser)},
                { error -> Log.e("error", "User not found!")})
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return allUsers
    }
}