package com.project.mvpkotlincrudroom.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "namadepan") val namadepan: String,
    @ColumnInfo(name = "namabelakang") val namabelakang: String,
    @ColumnInfo(name = "profesi") val profesi: String,
    @ColumnInfo(name = "tanggallahir") val tanggallahir: String,
    @ColumnInfo(name = "jk") val jk: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "nohp") val nohp: String,
    @ColumnInfo(name = "bahasa") val bahasa: String,
    @ColumnInfo(name = "hobi") val hobi: String
)