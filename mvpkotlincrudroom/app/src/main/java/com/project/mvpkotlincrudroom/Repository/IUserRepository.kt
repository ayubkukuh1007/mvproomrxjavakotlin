package com.project.mvpkotlincrudroom.Repository

import com.project.mvpkotlincrudroom.Room.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


interface IUserRepository {

    fun insert(user: User)

    fun update(user: User)

    fun delete(user: User)

    fun deleteAllUsers()

    fun getAllUsers(): Observable<List<User>>

    fun getUser(id : Int) : Single<User>

}