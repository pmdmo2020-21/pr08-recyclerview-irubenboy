package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding

class AddUserActivity : AppCompatActivity() {

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

    private val userBinding: UserActivityBinding by lazy { UserActivityBinding.inflate(layoutInflater) }
    private val userViewModel: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(userBinding.root)
        setupViews()
    }

    private fun setupViews() {
        Glide.with(this).load(userViewModel.getRandomPhotoUrl()).into(userBinding.imgAvatar)
    }

    private fun onSave() {
        // TODO: Acciones a realizar al querer salvar
    }

    companion object{
        fun newIntent(context: Context) = Intent(context, AddUserActivity::class.java)
    }
}