package com.example.icomerch.supplier

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.icomerch.databinding.SupplierHomeActivityBinding


class SupplierHomeActivity : AppCompatActivity() {

    private lateinit var binding : SupplierHomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SupplierHomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
