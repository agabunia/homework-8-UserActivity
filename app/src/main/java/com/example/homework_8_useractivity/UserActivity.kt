package com.example.homework_8_useractivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_8_useractivity.databinding.UserActivityBinding

class UserActivity: AppCompatActivity() {
    private lateinit var binding: UserActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    fun setUp() {
        val editFirstName = intent.getStringExtra("editFirstName")
        val editLastName = intent.getStringExtra("editLastName")
        val editEmail = intent.getStringExtra("editEmail")
        val position = intent.getStringExtra("position")

        if (editFirstName != null && editLastName != null && editEmail != null) {
            // Edit user
            val editUser: Int = 1
            binding.etFirstNameInput.setText(editFirstName)
            binding.etLastNameInput.setText(editLastName)
            binding.etEmailInput.setText(editEmail)

            binding.btnSaveInfo.setOnClickListener {
                val newFirstName = binding.etFirstNameInput.text.toString()
                val newLastName = binding.etLastNameInput.text.toString()
                val newEmail = binding.etEmailInput.text.toString()

                if (checkInputs(newFirstName, newLastName, newEmail)) {
                    val intent = Intent()
                    intent.putExtra("firstName", newFirstName)
                    intent.putExtra("lastName", newLastName)
                    intent.putExtra("email", newEmail)
                    intent.putExtra("position", position)
                    setResult(editUser, intent)
                    finish()
                } else {
                    binding.tvError.text = "All fields must be filled"
                }
            }
        } else {
            // Create user
            val createUser = 2
            binding.btnSaveInfo.setOnClickListener {
                val newFirstName = binding.etFirstNameInput.text.toString()
                val newLastName = binding.etLastNameInput.text.toString()
                val newEmail = binding.etEmailInput.text.toString()

                if (checkInputs(newFirstName, newLastName, newEmail)) {
                    val intent = Intent()
                    intent.putExtra("firstName", newFirstName)
                    intent.putExtra("lastName", newLastName)
                    intent.putExtra("email", newEmail)
                    setResult(createUser, intent)
                    finish()
                } else {
                    binding.tvError.text = "All fields must be filled"
                }
            }
        }
    }

    fun checkInputs(firstName: String?, lastName: String?, email: String?): Boolean {
        return !(firstName.isNullOrEmpty() || lastName.isNullOrEmpty() || email.isNullOrEmpty())
    }
}