package com.example.roomdatabase

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabase.adapter.RvAdapter
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.databinding.EditdialogBinding
import com.example.roomdatabase.models.MyDatabase
import com.example.roomdatabase.models.My_Contact

class MainActivity : AppCompatActivity() {
    lateinit var rvAdapter: RvAdapter
    lateinit var myDatabase: MyDatabase
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            btnSave.setOnClickListener {
                val name = name.text.toString()
                val surname = surname.text.toString()
                myDatabase = MyDatabase.getInstance(this@MainActivity)
                myDatabase.myDao().addContact(My_Contact(name, surname))
                onResume()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        myDatabase = MyDatabase.getInstance(this)
        rvAdapter = RvAdapter(
            myDatabase.myDao().getAllContacts() as ArrayList,
            object : RvAdapter.RvAction {
                override fun delete(myContact: My_Contact) {
                    myDatabase.myDao().deleteContact(myContact)
                    onResume()
                }

                override fun edit(myContact: My_Contact) {
                   showEditDialog(myContact)
                }
            })
        binding.rv.adapter = rvAdapter
    }

    private fun showEditDialog(contact: My_Contact) {
        val dialogBinding = EditdialogBinding.inflate(layoutInflater)

        dialogBinding.nameEditText.setText(contact.name)
        dialogBinding.surnameEditText.setText(contact.surname)

        val builder = AlertDialog.Builder(this)
            .setTitle("Edit Contact")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { dialogInterface: DialogInterface, i: Int ->
                val updatedName = dialogBinding.nameEditText.text.toString()
                val updatedSurname = dialogBinding.surnameEditText.text.toString()

                contact.name = updatedName
                contact.surname = updatedSurname
                myDatabase.myDao().editContact(contact)

                onResume()
            }
            .setNegativeButton("Cancel", null)
        builder.create().show()
    }
}