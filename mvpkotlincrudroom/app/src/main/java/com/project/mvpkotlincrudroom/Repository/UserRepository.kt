package com.project.mvpkotlincrudroom.Repository

import android.annotation.SuppressLint
import android.util.Log
import com.project.mvpkotlincrudroom.Room.User
import com.project.mvpkotlincrudroom.Room.UserDao
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserRepository(private val userDao: UserDao) :IUserRepository {


    @SuppressLint("CheckResult")
    override fun insert(user: User) {
        userDao.insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.e("RxJava", "Insert Success") },
                { error -> Log.e("Rxjava",error.message) }
            )
    }

    @SuppressLint("CheckResult")
    override fun update(user: User) {
        userDao.update(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Update Success") },
                { error -> Log.e("Rxjava",error.message) }
            )
    }

    @SuppressLint("CheckResult")
    override fun delete(user: User) {
        userDao.delete(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Delete Success") },
                {error -> Log.e("Rxjava",error.message) }
            )
    }

    override fun getUser(id: Int): Single<User> {
        return userDao.getUserById(id)
    }

    @SuppressLint("CheckResult")
    override fun deleteAllUsers() {
        Completable.fromAction{ userDao.deleteAllusers() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Delete all Success") },
                { error -> Log.e("Rxjava",error.message) }
            )
    }

    override fun getAllUsers(): Observable<List<User>> = userDao.getAllUsers()

}