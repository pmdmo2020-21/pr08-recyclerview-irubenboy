package es.iessaladillo.pedrojoya.pr06.ui.add_user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Database
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UserActivityBinding
import java.util.*

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
    private val userViewModel: AddUserViewModel by viewModels { AddUserViewModelFactory(Database) }
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(userBinding.root)
        setupViews()
    }

    private fun setupViews() {
        showImage()
        userBinding.imgAvatar.setOnClickListener { changeImage() }
    }

    private fun showImage() {
        Glide.with(this).load(userViewModel.img).into(userBinding.imgAvatar)
    }

    private fun changeImage() {
        userViewModel.changeImage()
        showImage()
    }

    private fun onSave() {
        if(isInvalidData()){
            Snackbar.make(userBinding.root, getString(R.string.user_invalid_data), Snackbar.LENGTH_LONG).show()
        } else {
            addUser()
            navigateToUsers()
        }
    }

    private fun addUser() {
        val name = userBinding.inputName.text.toString()
        val email = userBinding
                .inputEmail.text.toString()
        val phoneNumber = userBinding.inputTel.text.toString()
        val address = if (userBinding.inputAddress.text.isEmpty()) null else userBinding
                .inputAddress.text.toString()
        val web = if (userBinding.inputWeb.text.isEmpty()) null else userBinding.inputWeb.text.toString()
        val user = User(random.nextLong()+10, name, email, phoneNumber, address, web,
                userViewModel.img)
        userViewModel.addUser(user)
    }

    private fun navigateToUsers() {

    }

    private fun isInvalidData(): Boolean =  userBinding.inputName.text.isEmpty() || userBinding
            .inputEmail.text.isEmpty() || userBinding.inputTel.text.isEmpty()

    companion object{
        fun newIntent(context: Context) = Intent(context, AddUserActivity::class.java)
    }
}