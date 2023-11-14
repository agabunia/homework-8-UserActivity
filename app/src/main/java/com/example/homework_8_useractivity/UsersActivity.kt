package com.example.homework_8_useractivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_8_useractivity.databinding.UsersActivityBinding

class UsersActivity: AppCompatActivity(), UserActivityAdapter.ClickListener {
    private lateinit var binding: UsersActivityBinding
    lateinit var rvAdapter: UserActivityAdapter

    var users = mutableListOf(
        Users("James", "Bond", "james.bond.007@mail.com"),
        Users("Richard", "Gere", "rg@mail.com"),
        Users("Harry", "Potter", "hp@mail.com"),
        Users("David", "Gilmour", "pf@mail.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsersActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    fun setUp() {
        connectAdapter()

        binding.btnAddUser.setOnClickListener {
            getAction.launch(Intent(this, UserActivity::class.java))
        }
    }

    fun connectAdapter() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        rvAdapter = UserActivityAdapter(users, this)
        binding.rvUsers.adapter = rvAdapter
    }

    val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == 2) {
            val getFirstName = result.data!!.getStringExtra("firstName")
            val getLastName = result.data!!.getStringExtra("lastName")
            val getEmail = result.data!!.getStringExtra("email")
            val newUser = Users(getFirstName!!, getLastName!!, getEmail!!)
            users.add(newUser)
            rvAdapter.notifyItemInserted(users.size - 1)
        } else {
            val getFirstName = result.data!!.getStringExtra("firstName")!!
            val getLastName = result.data!!.getStringExtra("lastName")!!
            val getEmail = result.data!!.getStringExtra("email")!!
            val position = result.data!!.getStringExtra("position")!!.toInt()

            users[position].apply {
                firstName = getFirstName
                lastName = getLastName
                email = getEmail
            }
            rvAdapter.notifyItemChanged(position)
        }
    }

    override fun clickedUser(position: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("editFirstName", users[position].firstName)
        intent.putExtra("editLastName", users[position].lastName)
        intent.putExtra("editEmail", users[position].email)
        intent.putExtra("position", position)
        getAction.launch(intent)
    }
}