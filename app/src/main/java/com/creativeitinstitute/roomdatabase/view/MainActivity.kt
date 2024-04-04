package com.creativeitinstitute.roomdatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.creativeitinstitute.roomdatabase.UserAdapter
import com.creativeitinstitute.roomdatabase.databinding.ActivityMainBinding
import com.creativeitinstitute.roomdatabase.db.User
import com.creativeitinstitute.roomdatabase.db.UserDao
import com.creativeitinstitute.roomdatabase.db.UserDatabase

class MainActivity : AppCompatActivity(), UserAdapter.HandleUserClick {
    lateinit var binding: ActivityMainBinding



    private lateinit var dao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "User_DB"
        ).allowMainThreadQueries().build()

        dao = db.getUserDao()

        setUserData()









        binding.userBtn.setOnClickListener {

            val intent = Intent(this@MainActivity, AddUserActivity::class.java)
            startActivity(intent)

            }
    }

    private fun setUserData() {
        dao.getAllUser().apply {
            val userAdapter =  UserAdapter(this@MainActivity,this)
            binding.rvUser.adapter = userAdapter
        }
    }
    //jkhgskgswjkwekufwefhkljdfkjjkrleg

    override fun onEditClick(user: User) {
        val editIntent = Intent(this@MainActivity, AddUserActivity::class.java)

        editIntent.putExtra(AddUserActivity.editKey, user)
        startActivity(editIntent)
    }

    override fun onLongDeleteClick(user: User) {
        dao.deleteUser(user)
        Toast.makeText(this@MainActivity, "${user.name} data has been deleted", Toast.LENGTH_LONG).show()
        setUserData()
    }
}

