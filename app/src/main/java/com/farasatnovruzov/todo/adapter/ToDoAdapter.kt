package com.farasatnovruzov.todo.adapter

//import com.farasatnovruzov.todo.view.DetailsActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.farasatnovruzov.todo.databinding.RecyclerRowBinding
import com.farasatnovruzov.todo.model.ToDo
import com.farasatnovruzov.todo.view.ToDoListFragmentDirections

class ToDoAdapter(val toDoList: List<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoHolder>() {

    class ToDoHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoHolder(binding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
//        holder.binding.recyclerRowTextView.text = toDoList.get(position).title
//        holder.binding.recyclerRowTextView.text = todoList.get(position).id.toString()
//        println("added")
//        holder.itemView.setOnClickListener {
//            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
////            MySingleton.selectedToDo = todoList.get(position)
//
//            intent.putExtra("info", "old")
//            intent.putExtra("selectedToDo", toDoList[position])
//            holder.itemView.context.startActivity(intent)
//
//        }

        holder.binding.recyclerRowTextView.text = toDoList[position].title
        holder.binding.recyclerRowTimeView.text = toDoList[position].added_time

        holder.itemView.setOnClickListener {
//            if(toDoList.size != 0){
            val action = ToDoListFragmentDirections.actionToDoListFragmentToDetailsFragment(
                toDoList[position].id,
                "old"
            )
            Navigation.findNavController(it).navigate(action)
            println("position" + position)
//                add_todo = true

//            }

        }


//        holder.binding.delete.setOnClickListener {
////            println("deleted")
////            todoList[position].title
////            notifyDataSetChanged()
//
//            try {
//
//                val database = holder.itemView.context.openOrCreateDatabase("Todos",Context.MODE_PRIVATE,null)
//
//                var todoId = todoList.get(position).id
//                database.execSQL("DELETE FROM todos WHERE id = 1")
////                database.delete("todos","id",null)
//
//
////                val sqlString = "INSERT INTO todos(title,description) VALUES(?,?)"
////                val statement = database.compileStatement(sqlString)
////                statement.bindString(1,title)
////                statement.bindString(2,description)
////                statement.execute()
//
//
////                val cursor = database.rawQuery("SELECT * FROM todos", null)
////
////                val titleIx = cursor.getColumnIndex("title")
////                val idIx = cursor.getColumnIndex("id")
////
////
////                while (cursor.moveToNext()) {
////                    val title = cursor.getString(titleIx)
////                    val id = cursor.getInt(idIx)
////                    val toDo = ToDo(title, id)
////                    todoList.remove(toDo)
//////                    database.delete("Todos","id"= {getItemId(position)},null)
////                    database.delete("todos","id",null)
////                    println("deleted todo")
////                }
//
//                notifyDataSetChanged()
//
////                cursor.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

    }


}