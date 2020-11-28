package es.iessaladillo.pedrojoya.pr06.ui.add_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.model.User
import java.util.*


//  Crear la clase EditUserViewModel. Ten en cuenta que la url de la photo
//  deberá ser preservada por si la actividad es destruida por falta de recursos.

class AddUserViewModel: ViewModel() {
    private val random = Random()

    // Para obtener un URL de foto de forma aleatoria (tendrás que definir
    // e inicializar el random a nivel de clase.
    fun getRandomPhotoUrl(): String =
            "https://picsum.photos/id/${random.nextInt(100)}/400/300"

}
