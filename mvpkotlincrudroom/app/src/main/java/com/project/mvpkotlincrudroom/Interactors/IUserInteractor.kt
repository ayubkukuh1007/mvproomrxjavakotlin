package com.project.mvpkotlincrudroom.Interactors

import com.project.mvpkotlincrudroom.Room.User
import io.reactivex.Observable
import io.reactivex.Single

interface IUserInteractor {

    fun insert(user: User)

    fun update(user: User)

    fun delete(user: User)

    fun deleteAllQuotes()

    fun getAllUsers(): Observable<List<User>>

    fun getUserbyid(id : Int) : Single<User>
}