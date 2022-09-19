package peasynotes.notestaking.noteswriter.app.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import peasynotes.notestaking.noteswriter.app.feature_note.data.data_source.NoteDao
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note
import peasynotes.notestaking.noteswriter.app.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(val noteDao: NoteDao):NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        deleteNote(note)
    }
}