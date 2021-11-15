package com.farasatnovruzov.todo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.room.Room
import com.farasatnovruzov.todo.databinding.FragmentDetailsBinding
import com.farasatnovruzov.todo.model.ToDo
import com.farasatnovruzov.todo.roomdb.ToDoDao
import com.farasatnovruzov.todo.roomdb.ToDoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!
    private lateinit var toDoDatabase: ToDoDatabase
    private lateinit var toDoDao: ToDoDao
    private val mDisposable = CompositeDisposable()
    var toDoFromMain: ToDo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toDoDatabase =
            Room.databaseBuilder(requireContext(), ToDoDatabase::class.java, "ToDo").build()
        toDoDao = toDoDatabase.toDoDao()

//        val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
//        println("time: "+currentTime)


//        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
//        val formattedDate = df.format(c)
//        println("date time: "+formattedDate)

        val c = Calendar.getInstance().time
        println("Current time => $c")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_details, container, false)

        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener { save(view) }
        binding.delete.setOnClickListener { delete(view) }


        arguments?.let {
            val info = DetailsFragmentArgs.fromBundle(it).info
            if (info.equals("new")) {
                //NEW
                binding.titleText.setText("")
                binding.descriptionText.setText("")
                binding.save.visibility = View.VISIBLE
                binding.delete.visibility = View.GONE


            } else {
                //OLD
                binding.save.visibility = View.GONE
                binding.delete.visibility = View.VISIBLE

                val selectedId = DetailsFragmentArgs.fromBundle(it).id
                println(selectedId)
                mDisposable.add(
                    toDoDao.getToDoById(selectedId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponseWithOldToDo)
                )

            }
        }
    }

    private fun handleResponse() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToToDoListFragment()
        Navigation.findNavController(requireView()).navigate(action)
        println("saved!")
        add_todo = true
    }

//    private fun handleResponseWithOldArt(toDoList : List<ToDo>) {
//        toDoFromMain = toDo
//        binding.artText.setText(art.artName)
//        binding.artistText.setText(art.artistName)
//        binding.yearText.setText(art.year)
//}

    private fun handleResponseWithOldToDo(toDo: ToDo) {
        toDoFromMain = toDo
        binding.titleText.setText(toDo.title)
        binding.descriptionText.setText(toDo.description)
        add_todo = false
//        val listFragment = ToDoListFragment()
//        listFragment.recyclerFragmentView.layoutManager = LinearLayoutManager(requireContext())
//        val adapter = ToDoAdapter(toDo)
//        listFragment.recyclerFragmentView.adapter = adapter
//
//        navigationController = Navigation.findNavController(this, R.id.fragmentContainerView)
//        NavigationUI.setupActionBarWithNavController(this,navigationController)
//
    }


    fun delete(view: View) {

        toDoFromMain?.let {
            mDisposable.add(
                toDoDao.delete(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse)

            )

        }
    }

    fun save(view: View) {

//        val toDo = ToDo(
//            binding.titleText.text.toString(),
//            binding.descriptionText.text.toString()
//        )
//        mDisposable.add(
//            toDoDao.insert(toDo)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResponse)
//        )

        val toDoTitle = binding.titleText.text.toString()
        val toDoDescription = binding.descriptionText.text.toString()

        val added_time = Calendar.getInstance().time.toString()
        println("Current time => $added_time")
        if (!toDoTitle.isEmpty() || !toDoTitle.trim()
                .equals("") || !toDoTitle.isBlank()
        ) {


            val toDo = ToDo(toDoTitle, toDoDescription, added_time)

            mDisposable.add(
                toDoDao.insert(toDo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse)
            )

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

