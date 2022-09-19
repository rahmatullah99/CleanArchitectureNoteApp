package peasynotes.notestaking.noteswriter.app.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import peasynotes.notestaking.noteswriter.app.ui.theme.BabyBlue
import peasynotes.notestaking.noteswriter.app.ui.theme.LightGreen
import peasynotes.notestaking.noteswriter.app.ui.theme.RedOrange
import peasynotes.notestaking.noteswriter.app.ui.theme.Violet
import java.sql.Timestamp

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp: Long,
    val color:Int,
    @PrimaryKey
    val id:Int?=null
){

    companion object{
        val colors = listOf(Color.LightGray, RedOrange, BabyBlue, Violet, LightGreen )
    }

}

class InvalidNoteException(message:String):Exception(message)
