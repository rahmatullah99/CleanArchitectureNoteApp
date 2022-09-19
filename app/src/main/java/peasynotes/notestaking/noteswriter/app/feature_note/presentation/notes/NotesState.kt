package peasynotes.notestaking.noteswriter.app.feature_note.presentation.notes

import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.NoteOrder
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.OrderType

data class NotesState(
    val notes:List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.descending),
    val isOrderVisible:Boolean = false
)