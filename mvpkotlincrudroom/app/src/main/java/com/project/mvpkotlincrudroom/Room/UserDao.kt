package com.project.mvpkotlincrudroom.Room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UserDao {

    /*@Query("SELECT * from user_table")
    fun getUsers(): List<User>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User) : Completable

    @Update
    fun update(user: User) : Completable

    @Query("DELETE FROM user_table")
    fun deleteAllusers()

    @Delete
    fun delete(user: User) : Completable

    @Query("SELECT * FROM user_table WHERE id=:userId")
    fun getUserById(userId: Int?): Single<User>

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun getAllUsers(): Observable<List<User>>
}