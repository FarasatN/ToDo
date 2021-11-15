package com.farasatnovruzov.todo.view
//
//import android.content.Context
//import android.content.Intent
//import android.database.sqlite.SQLiteDatabase
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import androidx.room.Room
//import com.farasatnovruzov.todo.databinding.ActivityDetailsBinding
//import com.farasatnovruzov.todo.model.ToDo
//import com.farasatnovruzov.todo.roomdb.ToDoDao
//import com.farasatnovruzov.todo.roomdb.ToDoDatabase
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
////import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
////import io.reactivex.rxjava3.disposables.CompositeDisposable
////import io.reactivex.rxjava3.schedulers.Schedulers
//import java.lang.Exception
//
//class DetailsActivity : AppCompatActivity() {
//
//    private lateinit var binding : ActivityDetailsBinding
////    lateinit var todoList2 : ArrayList<ToDo>
////    private lateinit var database : SQLiteDatabase
//    private val compositeDisposable = CompositeDisposable()
//    private lateinit var db: ToDoDatabase
//    private lateinit var toDoDao: ToDoDao
//    var toDoFromMain: ToDo? = null
//
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailsBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        db = Room.databaseBuilder(applicationContext, ToDoDatabase::class.java, "ToDo")
//            .allowMainThreadQueries()
//            .build()
//        toDoDao = db.toDoDao()
//
////        binding.save.isEnabled = false
//
//
////        database = this.openOrCreateDatabase("Todos", Context.MODE_PRIVATE,null)
//
//        val intent = intent
//////        val todo = intent.getSerializableExtra("todo") as ToDo?
////
//        val info = intent.getStringExtra("info")
//
//        if (info.equals("new")) {
//            binding.titleText.setText("")
//            binding.descriptionText.setText("")
//            binding.save.visibility = View.VISIBLE
//            binding.delete.visibility = View.GONE
//
//        } else if (info.equals("old")) {
//////            val toDo = MySingleton.selectedToDo
//////            binding.titleText.setText(todo?.title )
//////            binding.descriptionText.setText(todo?.id)
////            binding.save.visibility = View.INVISIBLE
//
//
//            toDoFromMain = intent.getSerializableExtra("selectedToDo") as ToDo?
//            toDoFromMain.let {
//                binding.titleText.setText(it!!.title)
//                binding.descriptionText.setText(it.description)
//                binding.save.visibility = View.GONE
//                binding.delete.visibility = View.VISIBLE
//            }
//
////            val selectedId = intent.getIntExtra("id",1)
////
////            val cursor = database.rawQuery("SELECT * FROM todos WHERE id = ?", arrayOf(selectedId.toString()))
////
////            val nameIx = cursor.getColumnIndex("title")
////            val descriptionIx = cursor.getColumnIndex("description")
////
////            while (cursor.moveToNext()) {
////                binding.titleText.setText(cursor.getString(nameIx))
////                binding.descriptionText.setText(cursor.getString(descriptionIx))
////            }
////
////            cursor.close()
////
////        }
//
//
//        }
//    }
//
//    private fun handleResponse() {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
//    }
//
//
//    fun save(view: View){
//
//
//        //Main Thread UI, Default -> CPU, IO -> Internet/Database,
//        val toDo = ToDo(
//            binding.titleText.text.toString(),
//            binding.descriptionText.text.toString()
//        )
//        compositeDisposable.add(
//            toDoDao.insert(toDo)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResponse)
//
//        )
//
////        val title = binding.titleText.text.toString()
////        val description = binding.titleText.text.toString()
////
////        if(!(title.equals(null) || title.trim() == "" || title.isEmpty())){
////            try {
////                val database = this.openOrCreateDatabase("Todos", MODE_PRIVATE,null)
////                database.execSQL("CREATE TABLE IF NOT EXISTS todos(id INTEGER PRIMARY KEY,title VARCHAR, description VARCHAR)")
////                val sqlString = "INSERT INTO todos(title,description) VALUES(?,?)"
////                val statement = database.compileStatement(sqlString)
////                statement.bindString(1,title)
////                statement.bindString(2,description)
////                statement.execute()
////
////            }catch (e:Exception){
////                e.printStackTrace()
////            }
////
////            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////            startActivity(intent)
////        }
//
//    }
//
//    fun delete(view: View){
//
//        toDoFromMain?.let {
//            compositeDisposable.add(
//                toDoDao.delete(it)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(this::handleResponse)
//            )
//
//        }
//
//
////
//////        val title = binding.titleText.text.toString()
//////        val description = binding.titleText.text.toString()
////
////        val intent1 = intent
//////        val todo = intent.getSerializableExtra("todo") as ToDo?
////
////        val info = intent1.getStringExtra("info")
////
////        if((info.equals("old"))){
////            try {
////
////                val selectedId = intent.getIntExtra("id",1)
////
////                val database = this.openOrCreateDatabase("Todos", MODE_PRIVATE,null)
////
//////            database.delete("todos","id",null)
////                println(selectedId)
////                database.execSQL("DELETE FROM todos  WHERE id = ${selectedId}")
////
////            }catch (e:Exception){
////                e.printStackTrace()
////            }
////
////            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////            startActivity(intent)
////
////        }
//
//
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        compositeDisposable.clear()
//    }
//}