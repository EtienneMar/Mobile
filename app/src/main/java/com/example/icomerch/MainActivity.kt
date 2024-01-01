package com.example.icomerch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.icomerch.auth.view.ConnectionActivity
import com.example.icomerch.auth.view.CreateAccountActivity
import com.example.icomerch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gestion du clic sur le bouton de création de compte
        binding.buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        // Gestion du clic sur le bouton de connexion
        binding.buttonConnection.setOnClickListener {
            val intent = Intent(this, ConnectionActivity::class.java)
            startActivity(intent)
        }
    }
}
