package com.assigment.simioseiSot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assigment.simioseiSot.ViewModel.NoteItem
import com.assigment.simioseiSot.ViewModel.UpNoteAdapter
import com.assigment.simioseiSot.databinding.ActivityAllUpNotesBinding
import com.assigment.simioseiSot.databinding.DialogUpdateUpnoeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllUpNotes : AppCompatActivity(), UpNoteAdapter.OnItemClickListener  {
    private val binding:ActivityAllUpNotesBinding by lazy {
        ActivityAllUpNotesBinding.inflate(layoutInflater)
    }

      private lateinit var databaseReference: DatabaseReference
      private lateinit var auth: FirebaseAuth
      private lateinit var recyclerView: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



          recyclerView = binding.upNotesRecyclerView
          recyclerView.layoutManager = LinearLayoutManager(this)

        //initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference
        auth =FirebaseAuth.getInstance()


        val currentUser :FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val upnoteReference :DatabaseReference = databaseReference.child("users").child(user.uid).child("notes")
            upnoteReference.addValueEventListener(object :ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {
                    val upnoteList :MutableList<NoteItem> = mutableListOf<NoteItem>()
                    for (upnoteSnapshot in snapshot.children) {
                        val note :NoteItem?  = upnoteSnapshot.getValue(NoteItem::class.java)
                        note?.let {
                            upnoteList.add(it)


                        }


                    }
                    upnoteList.reverse()
                    val uadapter = UpNoteAdapter(upnoteList, this@AllUpNotes)
                    recyclerView.adapter = uadapter
                    // add log statements to check if data is retrieved correctly
                    Log.d("AllUpNotes","Data retrieved: $upnoteList")

                }

                override fun onCancelled(error: DatabaseError) {


                }

            })


        }


    }

    override fun onDeleteClick(noteId: String) {

        val currentUser :FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val noteRefrence :DatabaseReference = databaseReference.child("users").child(user.uid).child("notes")
                noteRefrence.child(noteId).removeValue()


        }

    }

    override fun onUpdateClick(noteId: String, currentTitle:String,currentDescription:String) {

        val dialogBinding :DialogUpdateUpnoeBinding = DialogUpdateUpnoeBinding.inflate(LayoutInflater.from(this))
        val dialog  = AlertDialog.Builder(this).setView(dialogBinding.root)
            .setTitle("Update UpNotes")
            .setPositiveButton("Update") {dialog, _ ->

                val newTitle = dialogBinding.updatenoteutitle.text.toString()
                val newDescription = dialogBinding.updatenoteudescription.text.toString()
                updateUNoteDatabase(noteId,newTitle,newDescription)
                dialog.dismiss()

            }
            .setNegativeButton("Cancel1") {dialog,_->

                dialog.dismiss()

            }
            .create()
        dialogBinding.updatenoteutitle.setText(currentTitle)
        dialogBinding.updatenoteudescription.setText(currentDescription)

        dialog.show()






    }

    private fun updateUNoteDatabase(noteId: String, newTitle: String, newDescription: String) {

        val currentUser :FirebaseUser? = auth.currentUser
        currentUser?.let { user ->
            val noteReference :DatabaseReference =databaseReference.child("users").child(user.uid).child("notes")
            val updateNote = NoteItem(newTitle,newDescription,noteId)
            noteReference.child(noteId).setValue(updateNote)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "UpNote Updated successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Fail to Update", Toast.LENGTH_SHORT).show()
                    }

                }



        }

    }
}

