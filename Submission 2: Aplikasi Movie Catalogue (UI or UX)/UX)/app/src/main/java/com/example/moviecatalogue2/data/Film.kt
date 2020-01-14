import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    var name:String = "",
    var popularity : String = "",
    var date : String = "",
    var desk:String = "",
    var photo:String = "",
    var director:String = ""
) : Parcelable
