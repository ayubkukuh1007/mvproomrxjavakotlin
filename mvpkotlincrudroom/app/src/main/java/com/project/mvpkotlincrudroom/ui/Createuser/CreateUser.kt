package com.project.mvpkotlincrudroom.ui.Createuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.mvpkotlincrudroom.Interactors.IUserInteractor
import com.project.mvpkotlincrudroom.Interactors.UserInteractor
import com.project.mvpkotlincrudroom.Presenter.IUserPresenter
import com.project.mvpkotlincrudroom.Presenter.UserPresenter
import com.project.mvpkotlincrudroom.R
import com.project.mvpkotlincrudroom.Repository.IUserRepository
import com.project.mvpkotlincrudroom.Repository.UserRepository
import com.project.mvpkotlincrudroom.Room.User
import com.project.mvpkotlincrudroom.Room.UserRoomDatabase
import com.project.mvpkotlincrudroom.ui.Readuser.ReadUser
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUser : AppCompatActivity() {

    lateinit var userPresenter: IUserPresenter
    lateinit var iUserRepository : IUserRepository
    lateinit var iUserInteractor : IUserInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val userDao = UserRoomDatabase.getDatabase(this).userDao();
        iUserRepository = UserRepository(userDao)
        iUserInteractor = UserInteractor(iUserRepository as UserRepository)
        userPresenter = UserPresenter(iUserInteractor)

        simpan.setOnClickListener { view ->

            val user = User(null,
                namadepan.text.toString(),
                namabelakang.text.toString(),
                profesi.text.toString(),
                tgllahir.text.toString(),
                if (laki.isChecked) {"Laki-laki"} else {"Perempuan"},
                if (menikah.isChecked) {"Menikah"} else {"Belum Menikah"},
                email.text.toString(),
                nohp.text.toString(),
                bahasa.text.toString(),
                hobi.text.toString()
            )

            userPresenter.insert(user)

            Toast.makeText(applicationContext,"data user berhasil disimpan !",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, ReadUser::class.java))
        }

        //jenis kelamin
        laki.setOnClickListener { view -> perempuan.isChecked = false }

        perempuan.setOnClickListener { view -> laki.isChecked = false }

        //status penikahan
        menikah.setOnClickListener { view -> bmenikah.isChecked = false }

        bmenikah.setOnClickListener { view -> menikah.isChecked = false}
    }

}
