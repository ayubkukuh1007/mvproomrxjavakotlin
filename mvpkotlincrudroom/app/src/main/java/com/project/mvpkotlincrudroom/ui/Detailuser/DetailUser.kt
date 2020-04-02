package com.project.mvpkotlincrudroom.ui.Detailuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.project.mvpkotlincrudroom.ui.Updateuser.Updateuser
import kotlinx.android.synthetic.main.activity_detail_user.*

class DetailUser : AppCompatActivity() {

    lateinit var userPresenter: IUserPresenter
    lateinit var iUserRepository : IUserRepository
    lateinit var iUserInteractor : IUserInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val userDao = UserRoomDatabase.getDatabase(this).userDao();
        iUserRepository = UserRepository(userDao)
        iUserInteractor = UserInteractor(iUserRepository as UserRepository)
        userPresenter   = UserPresenter(iUserInteractor)

        val iduser : Int? = intent.getIntExtra(ID_USER,0)

        iduser?.let { (userPresenter as UserPresenter).getUserbyid(it) }

        userPresenter.getUser().observe(this, Observer<User> { user ->
            pdnama.text         = user.namadepan
            namelengkap.text    = user.namadepan + user.namabelakang
            profesi.text        = user.profesi
            pdtanggallhr.text   = user.tanggallahir
            pdprofesi.text      = user.profesi
            pdjeniskelamin.text = user.jk
            pdstatus.text       = user.status
            cdemail.text        = user.email
            cdtelepon.text      = user.nohp
            mibahasa.text       = user.bahasa
            mihobi.text         = user.hobi
        })

        edit.setOnClickListener { view ->
            val intent = Intent(this, Updateuser::class.java)
            intent.putExtra(ReadUser.ID_USER,iduser)
            startActivity(intent)
        }
    }

    companion object {
        const val ID_USER = "ID_USER"
    }

}
