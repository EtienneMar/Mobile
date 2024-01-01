package com.example.icomerch.auth.view

import User
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.icomerch.R
import com.example.icomerch.auth.viewmodel.CreateAccountViewModel
import com.example.icomerch.databinding.CommonCreateAccountActivityBinding
import com.example.icomerch.model.Role
import com.example.icomerch.supplier.SupplierHomeActivity
import com.google.android.material.snackbar.Snackbar


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: CommonCreateAccountActivityBinding
    private lateinit var viewModel: CreateAccountViewModel
    private lateinit var inputsFields: List<EditText>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CreateAccountViewModel::class.java]
        binding = CommonCreateAccountActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onCreateRoleAutoComplete()
        onCreateInputFields()
        onCreateInputsListeners()
        binding.buttonConnection.isEnabled = areInputsValid()
        handleButtonConnectionListener()

        viewModel.snackbarMessage.observe(this) { message ->
            if (!message.isNullOrEmpty()) {
                // Utilisez le layout racine de l'activité comme ancre pour la Snackbar
                val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.maxLines = 3  // Nombre de lignes à afficher avant de défiler
                textView.movementMethod = ScrollingMovementMethod()  // Active le défilement
                snackbar.setAction("OK") { snackbar.dismiss() }
                snackbar.show()

            }
        }

        viewModel.navigateToSupplier.observe(this) { navigate ->
            if (navigate) {
                val intent = Intent(this, SupplierHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun onCreateInputFields(){
        inputsFields = listOf(
            binding.editRole,
            binding.editTextLastName,
            binding.editTextFirstName,
            binding.editTextMail,
            binding.editTextPassword,
            binding.editTextConfirmPassword,
            binding.editTextZipCode
        )
    }

    private fun onCreateRoleAutoComplete() {
        setupRoleAutoComplete()
        setupRoleSelectionListener()
    }

    private fun setupRoleAutoComplete() {
        val roles = Role.values().map { getString(it.roleNameResId) }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, roles)
        (binding.inputRole.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupRoleSelectionListener() {
        val autoCompleteTextView = binding.inputRole.editText as? AutoCompleteTextView
        autoCompleteTextView?.setOnItemClickListener { _, _, position, _ ->
            handleRoleSelection(Role.values()[position])
        }
    }

    private fun handleRoleSelection(selectedRole: Role) {
        when (selectedRole) {
            Role.SUPPLIER -> binding.buttonConnection.text = getString(R.string.Inscription)
            Role.CLIENT -> binding.buttonConnection.text = getString(R.string.next)
        }
    }

    private fun onCreateInputsListeners(){
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                binding.buttonConnection.isEnabled = areInputsValid()
            }
        }
        inputsFields.forEach { it.addTextChangedListener(textWatcher) }
    }

    private fun areInputsValid() : Boolean {
        return inputsFields.all { inputField ->
            inputField.text.toString().isNotBlank()
        }
    }

    private fun handleButtonConnectionListener(){
        binding.buttonConnection.setOnClickListener{
            var buttonText = binding.buttonConnection.text.toString()
            val user = createUserFromInput()
            when(buttonText){
                getString(R.string.Inscription) -> viewModel.handleInscription(user)
                getString(R.string.next) -> handleButtonNextToClientInterest(user)
            }
        }
    }

    private fun createUserFromInput(): User {
        val selectedRoleName = binding.inputRole.editText?.text.toString()
        return viewModel.createUser(
            Role.fromString(selectedRoleName, this).toString(),
            binding.inputLastName.editText?.text.toString(),
            binding.inputFirstName.editText?.text.toString(),
            binding.inputMail.editText?.text.toString(),
            binding.inputPassword.editText?.text.toString(),
            binding.inputZipCode.editText?.text.toString()
        )
    }

    private fun handleButtonNextToClientInterest(user: User){
        val intent =  Intent(this, ClientInterestActivity::class.java)
        intent.putExtra("userInfo", user)
        startActivity(intent)
    }

}
