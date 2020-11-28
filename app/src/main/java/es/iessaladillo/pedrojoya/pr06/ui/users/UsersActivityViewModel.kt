package es.iessaladillo.pedrojoya.pr06.ui.users

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr06.data.DataSource
import es.iessaladillo.pedrojoya.pr06.data.model.User


//  Crear clase UsersActivityViewModel
class UsersViewModel(private val database: DataSource): ViewModel(){
    fun addUser(user: User){
        database.insertUser(user)
    }


}