package test

import kotlinx.android.parcel.*
import android.os.Parcel
import android.os.Parcelable

object Parceler1 : Parceler<String> {
    override fun create(parcel: Parcel) = parcel.readInt().toString()

    override fun String.write(parcel: Parcel, flags: Int) {
        parcel.writeInt(length)
    }
}

<!DEPRECATED_ANNOTATION!>@Parcelize<!>
data class Test(
    val a: <!FORBIDDEN_DEPRECATED_ANNOTATION!>@WriteWith<Parceler1><!> String,
    val b: <!FORBIDDEN_DEPRECATED_ANNOTATION!>@WriteWith<Parceler1><!> List<<!FORBIDDEN_DEPRECATED_ANNOTATION!>@WriteWith<Parceler1><!> String>
) : Parcelable