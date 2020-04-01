package com.project.mvpkotlincrudroom.Interactors

import android.util.Log
import com.project.mvpkotlincrudroom.Repository.UserRepository
import com.project.mvpkotlincrudroom.Room.User
import io.reactivex.Observable
import io.reactivex.Single

class UserInteractor(private val userRepository: UserRepository) : IUserInteractor {

    override fun insert(user: User) {
        userRepository.insert(user)
    }

    override fun update(user: User) {
        userRepository.update(user)
    }

    override fun delete(user: User) {
        userRepository.delete(user)
    }

    override fun deleteAllQuotes() {
        userRepository.deleteAllUsers()
    }

    override fun getAllUsers(): Observable<List<User>> {
        return userRepository.getAllUsers()
    }

    override fun getUserbyid(id: Int): Single<User> {
        return userRepository.getUser(id)
    }
}