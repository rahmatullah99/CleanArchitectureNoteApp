package peasynotes.notestaking.noteswriter.app.feature_note.domain.use_case

import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.InvalidNoteException
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note
import peasynotes.notestaking.noteswriter.app.feature_note.domain.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("Title is empty")
        }

        if(note.content.isBlank()){
            throw InvalidNoteException("Content is empty")
        }

        noteRepository.insertNote(note)


    }

}