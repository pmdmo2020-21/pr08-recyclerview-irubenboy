package es.iessaladillo.pedrojoya.pr06.ui.edit_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Database
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import java.util.*

class EditUserActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  Ten en cuenta que la actividad debe recibir a través del intent
    //  con el que es llamado el objeto User corresondiente
    //  ...

    // NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            onSave()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // FIN NO TOCAR

    private val editUserBinding: UserActivityBinding by lazy{ UserActivityBinding.inflate(layoutInflater)}
    private val editUserViewModel: EditUserViewModel by viewModels { EditUserViewModelFactory(Database)  }
    private val random = Random()

    private fun onSave() {
        // TODO: Acciones a realizar al querer salvar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(editUserBinding.root)
        getData()
        observe()
    }

    @Suppress("DEPRECATION")
    private fun observe() {
        editUserViewModel.userEditable.observe(this){showInfo(it)}
    }

    private fun getData() {
        if (intent == null || !intent.hasExtra(USER)) {
            throw RuntimeException(
                    "WinnerActivity needs to receive id as extras")
        }
        val user: User? = intent.getParcelableExtra(USER)

        if(user != null) editUserViewModel.getEditableUser(user)
    }

    private fun showInfo(user: User) {
        user.run{
            editUserBinding.inputName.setText(name)
            editUserBinding.inputEmail.setText(email)
            editUserBinding.inputTel.setText(phoneNumber)
            editUserBinding.inputWeb.setText(web?:"")
            editUserBinding.inputAddress.setText(address?:"")
            Glide.with(editUserBinding.root).load(photoUrl).into(editUserBinding.imgAvatar)
        }
    }

    companion object {
        private const val USER = "EXTRA_USER"
        fun newIntent(context: Context, userEditable: User) = Intent(context,
                EditUserActivity::class.java).apply {
            putExtras(bundleOf(USER to userEditable))
        }
    }

}