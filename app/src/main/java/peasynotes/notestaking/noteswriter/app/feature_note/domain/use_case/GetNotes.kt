package peasynotes.notestaking.noteswriter.app.feature_note.domain.use_case

import androidx.lifecycle.Transformations.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note
import peasynotes.notestaking.noteswriter.app.feature_note.domain.repository.NoteRepository
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.NoteOrder
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.OrderType

class GetNotes(private val repository: NoteRepository) {

        operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.descending))
                : Flow<List<Note>> {

                return repository.getNotes().map { notes->

                        when(noteOrder.orderType){
                                is OrderType.ascending -> {
                                        when(noteOrder) {
                                                is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                                                is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                                                is NoteOrder.Color -> notes.sortedBy { it.color }
                                        }
                                }
                                is OrderType.descending -> {
                                        when(noteOrder) {
                                                is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                                                is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                                                is NoteOrder.Color -> notes.sortedByDescending { it.color }
                                        }
                                }

                        }

                }

        }

}

