package com.assigment.simioseiSot.repository

import androidx.lifecycle.LiveData
import com.assigment.simioseiSot.Dao.NotesDao
import com.assigment.simioseiSot.model.Note
import kotlinx.coroutines.flow.Flow

class NotesRepo(val notesDao: NotesDao) {

    var allNotes : LiveData<List<Note>> = notesDao.getAllNotes()


    suspend fun addNote(note: Note){
        notesDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        notesDao.deleteNote(note)
    }

    fun searchNotes(query:String) : Flow<List<Note>> {
        var result = notesDao.searchDatabase(query)
        return  result
    }



}