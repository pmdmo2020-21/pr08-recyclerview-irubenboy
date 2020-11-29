package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User
import java.util.*

// TODO:
//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class EditUserViewModel(private val database: DataSource): ViewModel() {

    private val random = Random()
    private val _userEditable: MutableLiveData<User> = MutableLiveData()
    val userEditable: LiveData<User>
        get() = _userEditable

    val users: LiveData<List<User>> = database.getAllUsersOrderedByName()
    var img: String = getRandomPhotoUrl()

    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    private fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

    fun changeImage(){
        img = getRandomPhotoUrl()
    }

    fun getEditableUser(user: User) {
        _userEditable.value = user
    }
}
