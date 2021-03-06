package com.example.spectacle.ui.login

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spectacle.R
import com.example.spectacle.databinding.ActivityFormSubscribeBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import java.text.SimpleDateFormat
import java.util.*

class FormSubscribe : AppCompatActivity() {

    private var selectedDate = ""
    private lateinit var binding: ActivityFormSubscribeBinding
    private lateinit var billDateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormSubscribeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        billDateEditText = findViewById(R.id.billDate_editText_billUpload)

        supportActionBar!!.hide()

        showDatePicker()

        binding.btnSubscribe.setOnClickListener {

            val name = binding.editName.text.toString()
//            val billDateEditText = binding.billDateEditTextBillUpload.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val message_error = binding.messageErrorSubscribe
            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || selectedDate.isEmpty()) {
                message_error.setText("Preencha todos os campos!")
            } else {
                subscribeUser()
            }
        }
    }

    fun showDatePicker() {

        billDateEditText.setText(SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()))

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
//            billDateEditText.setText(sdf.format(cal.time))

            selectedDate = sdf.format(cal.time)
            billDateEditText.setText(selectedDate)
        }

        billDateEditText.setOnClickListener {

            Log.d("Clicked", "Interview Date Clicked")

            val dialog = DatePickerDialog(
                this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
    }

    private fun subscribeUser() {

        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val messageError = binding.messageErrorSubscribe

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Usu??rio cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                    binding.editName.setText("")
                    binding.billDateEditTextBillUpload.setText("")
                    binding.editEmail.setText("")
                    binding.editPassword.setText("")
                    messageError.setText("")
                }
            }.addOnFailureListener {

                val error = it

                when {
                    error is FirebaseAuthWeakPasswordException -> messageError.setText("Digite uma senha com no m??nimo 6 caracteres")
                    error is FirebaseAuthUserCollisionException -> messageError.setText("Esta conta j?? foi cadastrada")
                    error is FirebaseNetworkException -> messageError.setText("Sem conex??o com a internet")
                    else -> messageError.setText("Erro ao cadastrar usu??rio")
                }
            }
    }
}
