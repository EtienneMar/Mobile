package com.example.icomerch.auth.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.icomerch.databinding.ClientInterestActivityBinding

class ClientInterestActivity : AppCompatActivity() {

    private lateinit var binding: ClientInterestActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ClientInterestActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}