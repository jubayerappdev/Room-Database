package com.creativeitinstitute.roomdatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.creativeitinstitute.roomdatabase.R
import com.creativeitinstitute.roomdatabase.databinding.ActivityAddUserBinding
import com.creativeitinstitute.roomdatabase.db.User
import com.creativeitinstitute.roomdatabase.db.UserDao
import com.creativeitinstitute.roomdatabase.db.UserDatabase

class AddUserActivity : AppCompatActivity() {

    companion object{
        const val editKey = "edit"
        const val update = "Update User"
        const val add = "Add User"
    }

    private lateinit var binding: ActivityAddUserBinding
    private lateinit var dao:UserDao

    var userId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "User_DB"
        ).allowMainThreadQueries().build()

         dao = db.getUserDao()

        if (intent.hasExtra(editKey)){
            binding.btnAddUser.text = update

            var user = intent.getParcelableExtra<User>(editKey)
            binding.apply {
                etUserName.setText(user?.name)
                etUserAge.setText("${ user?.age ?: 0 }")
                etUserMobile.setText(user?.mobile)


                userId = user?.id?:0
            }
        }else{
            binding.btnAddUser.text = add
        }

        binding.btnAddUser.setOnClickListener{

            val name = binding.etUserName.text.toString()
            val age = binding.etUserAge.text.toString()
            val mobile = binding.etUserMobile.text.toString()

            if (binding.btnAddUser.text.toString()==add){
                addUser(name, age, mobile)
            }else{
                updateUser(name, age, mobile)
            }


        }

    }

    private fun updateUser(name: String, age: String, mobile: String) {
        val user = User(userId, name, age.toInt(), mobile)
        dao.editUser(user)
    }

    private fun addUser(name: String, age:String, mobile:String) {
        val user = User(0,name,age.toInt(),mobile)
        dao.addUser(user)

    }
}