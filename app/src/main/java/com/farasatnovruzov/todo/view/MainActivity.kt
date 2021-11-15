package com.farasatnovruzov.todo.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.farasatnovruzov.todo.R

var add_todo: Boolean = true

class MainActivity : AppCompatActivity() {


    private lateinit var navigationController: NavController

//    private lateinit var binding : ActivityMainBinding
//    private lateinit var todoList : ArrayList<ToDo>
//    private lateinit var todoAdapter: ToDoAdapter
//    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
        setContentView(R.layout.activity_main)

        navigationController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navigationController)


//        val db = Room.databaseBuilder(applicationContext,ToDoDatabase::class.java,"ToDo").build()
//        val placesDao = db.toDoDao()
//
//        compositeDisposable.add(
//            placesDao.getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResponse)
//        )


//        todoList = ArrayList<ToDos>()
//        todoAdapter = ToDoAdapter(todoList)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = todoAdapter

//        val pisa = ToDo("Pisa","Italy")
//        val colosseum = ToDo("Colloseum","Italy")
//        todoList.add(pisa)
//        todoList.add(colosseum)


//        //RecyclerView
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = ToDoAdapter(todoList)
//        binding.recyclerView.adapter = adapter
//
//        try {
//
//            val database = this.openOrCreateDatabase("Todos", Context.MODE_PRIVATE,null)
//
//            val cursor = database.rawQuery("SELECT * FROM todos",null)
//            val titleIx = cursor.getColumnIndex("title")
//            val idIx = cursor.getColumnIndex("id")
//
//
//            while (cursor.moveToNext()) {
//                val title = cursor.getString(titleIx)
//                val id = cursor.getInt(idIx)
//                val toDo = ToDos(title,id)
//                todoList.add(toDo)
//            }
//
//            todoAdapter.notifyDataSetChanged()
//
//            cursor.close()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }


    }


    //    private fun handleResponse(toDoList : List<ToDo>){
////        binding.recyclerView.layoutManager = LinearLayoutManager(this)
////        val adapter = ToDoAdapter(toDoList)
////        binding.recyclerView.adapter = adapter
//
//        navigationController = Navigation.findNavController(this, R.id.fragmentContainerView)
//        NavigationUI.setupActionBarWithNavController(this,navigationController)
//
//    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Inflater
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_todo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment)
        add_todo = true
        return navController.navigateUp()

    }


//        Inflater
//        val menuInflater = menuInflater
//        menuInflater.inflate(R.menu.add_todo,menu)
//        return super.onCreateOptionsMenu(menu)


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        if (item.itemId == R.id.add_todo_item) {
//            val intent = Intent(this, DetailsActivity::class.java)
//            intent.putExtra("info","new")
//            startActivity(intent)
//        }

        if (add_todo) {
            if (item.itemId == R.id.add_todo_item) {
                val action =
                    ToDoListFragmentDirections.actionToDoListFragmentToDetailsFragment(0, "new")
                Navigation.findNavController(this, R.id.fragment).navigate(action)
            }
            add_todo = false
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        return super.onPrepareOptionsMenu(menu)
    }


}