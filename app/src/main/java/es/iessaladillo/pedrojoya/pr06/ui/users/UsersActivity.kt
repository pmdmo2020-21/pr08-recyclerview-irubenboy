package es.iessaladillo.pedrojoya.pr06.ui.users

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.Database
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.databinding.UsersActivityBinding
import es.iessaladillo.pedrojoya.pr06.ui.add_user.AddUserActivity

class UsersActivity : AppCompatActivity() {

    // TODO: Código de la actividad.
    //  Ten en cuenta que el recyclerview se muestra en forma de grid,
    //  donde el número de columnas está gestionado
    //  por R.integer.users_grid_columns
    //  ...

    // NO TOCAR: Estos métodos gestionan el menú y su gestión

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.users, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuAdd) {
            onAddUser()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // FIN NO TOCAR

    private val usersBinding: UsersActivityBinding by lazy{
        UsersActivityBinding.inflate(layoutInflater)
    }
    private val listAdapter: UsersAdapter = UsersAdapter()
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(Database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(usersBinding.root)
        setupViews()
        observe()
    }


    private fun setupViews() {
        setupRecyclerView()
        usersBinding.iconAddUsers.setOnClickListener { onAddUser() }
    }


    private fun setupRecyclerView() {
        usersBinding.listUsers.run{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UsersActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun onAddUser() {
        val intent = AddUserActivity.newIntent(this)
        startActivity(intent)
    }

    @Suppress("DEPRECATION")
    private fun observe() {
        viewModel.users.observe(this){
            updateList(it)
        }
    }

    private fun updateList(users: List<User>){
        listAdapter.submitList(users)
        usersBinding.lblEmptyView.visibility = if(users.isEmpty()) View.VISIBLE else View.INVISIBLE
        usersBinding.iconAddUsers.visibility = if(users.isEmpty()) View.VISIBLE else View.INVISIBLE
    }
}
