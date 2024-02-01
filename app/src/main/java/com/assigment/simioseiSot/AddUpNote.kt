package com.assigment.simioseiSot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.assigment.simioseiSot.databinding.ActivityAddUpNoteBinding
import com.assigment.simioseiSot.ViewModel.NoteItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUpNote : AppCompatActivity() {

    private val binding: ActivityAddUpNoteBinding by lazy {
        ActivityAddUpNoteBinding.inflate(layoutInflater)
    }

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initilize firebase database reference

        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()



        binding.SaveUpNoteButton.setOnClickListener {


            //get text from edit text
            val title :String = binding.etTitle.text.toString()
            val description :String = binding.etDescription.text.toString()

            if (title.isEmpty() && description.isEmpty()) {

                Toast.makeText(this, "Fill Both Fields", Toast.LENGTH_SHORT).show()

            }else{

                val currentUser :FirebaseUser? = auth.currentUser
                currentUser?. let { user ->
                    // generate unique key for note
                    val noteKey :String? = databaseReference.child("users").child(user.uid).child("notes").push().key

                    //note item instance
                    val noteItem = NoteItem(title,description,noteKey ?:"")
                    if (noteKey!= null)

                        // add notes to the user note
                        databaseReference.child("users").child(user.uid).child("notes").child(noteKey).setValue(noteItem)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(this, "Note Save Successful", Toast.LENGTH_SHORT).show()
                                    finish()
                                }else{
                                    Toast.makeText(this, "Failed to Save Note", Toast.LENGTH_SHORT).show()
                                }
                            }


                }


            }


        }
    }
}