package peasynotes.notestaking.noteswriter.app.feature_note.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getNoteById(id:Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)

}