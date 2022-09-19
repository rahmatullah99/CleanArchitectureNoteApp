package peasynotes.notestaking.noteswriter.app.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase() {
    abstract val noteDao:NoteDao
    companion object{
        const val DB_NAME="note_db"
    }
}