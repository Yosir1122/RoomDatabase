package com.example.roomdatabase.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {
    @Insert
    fun addContact(my_contact: My_Contact)

    @Query("select * from My_Contact")
    fun getAllContacts(): List<My_Contact>

    @Delete
    fun deleteContact(my_contact: My_Contact)

    @Update
    fun editContact(my_contact: My_Contact)
}