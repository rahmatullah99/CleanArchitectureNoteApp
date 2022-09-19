package peasynotes.notestaking.noteswriter.app.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import peasynotes.notestaking.noteswriter.app.NoteApp
import peasynotes.notestaking.noteswriter.app.feature_note.data.data_source.NoteDatabase
import peasynotes.notestaking.noteswriter.app.feature_note.data.repository.NoteRepositoryImpl
import peasynotes.notestaking.noteswriter.app.feature_note.domain.repository.NoteRepository
import peasynotes.notestaking.noteswriter.app.feature_note.domain.use_case.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(noteApp: Application): NoteDatabase {
        return Room.databaseBuilder(
            noteApp,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME).build() }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(noteRepository) ,
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )

    }

}

