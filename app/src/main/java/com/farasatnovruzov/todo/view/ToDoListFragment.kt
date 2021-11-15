package com.farasatnovruzov.todo.view

//import com.farasatnovruzov.todo.databinding.FragmentListBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.farasatnovruzov.todo.adapter.ToDoAdapter
import com.farasatnovruzov.todo.databinding.FragmentToDoListBinding
import com.farasatnovruzov.todo.model.ToDo
import com.farasatnovruzov.todo.roomdb.ToDoDao
import com.farasatnovruzov.todo.roomdb.ToDoDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ToDoListFragment : Fragment() {

    private lateinit var toDoAdapter: ToDoAdapter
    private var _binding: FragmentToDoListBinding? = null
    private val binding get() = _binding!!
    private val mDisposable = CompositeDisposable()
    private lateinit var toDoDao: ToDoDao
    private lateinit var toDoDatabase: ToDoDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        artDatabase = Room.databaseBuilder(requireContext(), ArtDatabase::class.java, "Arts").build()
//        artDao = artDatabase.artDao()

        toDoDatabase =
            Room.databaseBuilder(requireContext(), ToDoDatabase::class.java, "ToDo").build()
        toDoDao = toDoDatabase.toDoDao()

//        mDisposable.add(
//            toDoDao.getToDoWithNameAndId()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResponse)
//        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false)

        _binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFromSQL()
    }

    fun getFromSQL() {
        mDisposable.add(
            toDoDao.getToDoWithNameIdAndTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }


    private fun handleResponse(toDoList: List<ToDo>) {
        binding.recyclerFragmentView.layoutManager = LinearLayoutManager(requireContext())
        toDoAdapter = ToDoAdapter(toDoList)
        binding.recyclerFragmentView.adapter = toDoAdapter

//        navigationController = Navigation.findNavController(this, R.id.fragmentContainerView)
//        NavigationUI.setupActionBarWithNavController(this,navigationController)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}