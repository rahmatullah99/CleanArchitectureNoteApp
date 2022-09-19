package peasynotes.notestaking.noteswriter.app.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import peasynotes.notestaking.noteswriter.app.feature_note.domain.model.Note
import peasynotes.notestaking.noteswriter.app.feature_note.domain.use_case.NoteUseCases
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.NoteOrder
import peasynotes.notestaking.noteswriter.app.feature_note.domain.util.OrderType
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(val noteUseCases: NoteUseCases): ViewModel() {

    val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    var recentlyDeletedNote: Note? = null
    var noteJob: Job? = null

    init {
        getNotesInOrder(NoteOrder.Date(OrderType.descending))
    }

    fun onEvent(notesEvent: NotesEvent){
        when(notesEvent){
            is NotesEvent.Order->{
                if(state.value.noteOrder::class==notesEvent.noteOrder::class &&
                        state.value.noteOrder.orderType==notesEvent.noteOrder.orderType){
                    return
                }

                getNotesInOrder(notesEvent.noteOrder)
            }

            is NotesEvent.DeleteNote->{
                viewModelScope.launch {
                    noteUseCases.deleteNote(notesEvent.note)
                    recentlyDeletedNote=notesEvent.note
                }
            }

            is NotesEvent.RestoreNote->{

                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote?:return@launch)
                    recentlyDeletedNote=null
                }

            }

            is NotesEvent.ToggleOrderSection->{
                _state.value = state.value.copy(
                    isOrderVisible = !state.value.isOrderVisible)
            }
        }
    }

    private fun getNotesInOrder(noteOrder: NoteOrder) {
        noteJob?.cancel()
        noteJob = noteUseCases.getNotes(noteOrder).onEach {

            _state.value = state.value.copy(
                notes = it,
                noteOrder = noteOrder)

        }.launchIn(viewModelScope)

    }

}