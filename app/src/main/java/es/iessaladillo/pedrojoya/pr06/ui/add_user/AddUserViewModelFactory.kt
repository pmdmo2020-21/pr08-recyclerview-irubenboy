package es.iessaladillo.pedrojoya.pr06.ui.add_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr06.data.DataSource


class AddUserViewModelFactory(private val database: DataSource): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddUserViewModel(database) as T
    }
}