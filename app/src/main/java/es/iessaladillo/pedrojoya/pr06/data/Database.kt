package es.iessaladillo.pedrojoya.pr06.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr06.data.model.User


//  Crear una singleton Database que implemente la interfaz DataSource.
//  Al insertar un usuario, se le asignará un id autonumérico
//  (primer valor válido será el 1) que deberá ser gestionado por la Database.
//  La base de datos debe ser reactiva, de manera que cuando se agrege,
//  actualice o borre un usuario, los observadores de la lista deberán
//  recibir la nueva lista.
//  Al consultar los usuario se deberá retornar un LiveData con la lista
//  de usuarios ordenada por nombre
object Database: DataSource{
    private val users: MutableList<User> = mutableListOf()

    private val usersLiveData: MutableLiveData<List<User>> = MutableLiveData(users.sortedBy { it.name })

    override fun getAllUsersOrderedByName(): LiveData<List<User>> = usersLiveData

    override fun insertUser(user: User) {
        users.add(user)
        updateUsersLiveData()
    }

    private fun updateUsersLiveData() {
        usersLiveData.value = ArrayList<User>(users.sortedBy { it.name })
    }

    override fun updateUser(user: User) {
        val position = users.indexOfFirst { it.id == user.id }
        if(position >= 0){
            users[position] = user
            updateUsersLiveData()
        }
    }

    override fun deleteUser(user: User) {
        val position = users.indexOfFirst { it.id == user.id }
        if(position >= 0){
            users.removeAt(position)
            updateUsersLiveData()
        }
    }

}
