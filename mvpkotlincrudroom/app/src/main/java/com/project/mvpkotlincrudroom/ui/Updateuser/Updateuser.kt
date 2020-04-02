package com.project.mvpkotlincrudroom.ui.Updateuser

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
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
import kotlinx.android.synthetic.main.activity_updateuser.*
import kotlinx.android.synthetic.main.activity_updateuser.bahasa
import kotlinx.android.synthetic.main.activity_updateuser.email
import kotlinx.android.synthetic.main.activity_updateuser.hobi
import kotlinx.android.synthetic.main.activity_updateuser.laki
import kotlinx.android.synthetic.main.activity_updateuser.menikah
import kotlinx.android.synthetic.main.activity_updateuser.namabelakang
import kotlinx.android.synthetic.main.activity_updateuser.namadepan
import kotlinx.android.synthetic.main.activity_updateuser.nohp
import kotlinx.android.synthetic.main.activity_updateuser.tgllahir

class Updateuser : AppCompatActivity() {

    lateinit var userPresenter: IUserPresenter
    lateinit var iUserRepository: IUserRepository
    lateinit var iUserInteractor: IUserInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateuser)

        val iduser : Int? = intent.getIntExtra(ID_USER,0)
        val userDao = UserRoomDatabase.getDatabase(this).userDao();

        iUserRepository = UserRepository(userDao)
        iUserInteractor = UserInteractor(iUserRepository as UserRepository)
        userPresenter = UserPresenter(iUserInteractor)

        iduser?.let { (userPresenter as UserPresenter).getUserbyid(it) }

        userPresenter.getUser().observe(this, Observer {user ->

            namadepan.setText(user.namadepan)
            namabelakang.setText(user.namabelakang)
            profesi.setText(user.profesi)
            tgllahir.setText(user.tanggallahir)

            if (user.jk == "Laki-laki") {
                laki.isChecked = true
            } else {
                laki.isChecked = false
                perempuan.isChecked = true
            }
            if (user.status == "Menikah") {
                menikah.isChecked = true
            } else {
                menikah.isChecked = false
                bmenikah.isChecked = true
            }

            email.setText(user.email)
            nohp.setText(user.nohp)
            bahasa.setText(user.bahasa)
            hobi.setText(user.hobi)
        })

        edituser.setOnClickListener { view ->
            val user = User(
                iduser,
                namadepan.text.toString(),
                namabelakang.text.toString(),
                profesi.text.toString(),
                tgllahir.text.toString(),

                if (laki.isChecked) {
                    "Laki-laki"
                } else {
                    "Perempuan"
                },
                if (menikah.isChecked) {
                    "Menikah"
                } else {
                    "Belum Menikah"
                },

                email.text.toString(),
                nohp.text.toString(),
                bahasa.text.toString(),
                hobi.text.toString()
            )

            userPresenter.update(user)

            Toast.makeText(applicationContext, "data user berhasil diedit !", Toast.LENGTH_SHORT)
                .show()

            startActivity(Intent(this, ReadUser::class.java))
        }

        //jenis kelamin
        laki.setOnClickListener { view -> perempuan.isChecked = false }

        perempuan.setOnClickListener { view -> laki.isChecked = false }

        //status penikahan
        menikah.setOnClickListener { view -> bmenikah.isChecked = false }

        bmenikah.setOnClickListener { view -> menikah.isChecked = false}

    }

    companion object {
        const val ID_USER = "ID_USER"
    }

}
