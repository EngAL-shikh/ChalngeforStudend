package com.example.chalngeforcrime

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


private const val TAG = "StudentListFragment"


class StudentListFragment : Fragment(),inputD.Callbacks {

    lateinit var add: FloatingActionButton
    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter? = StudentAdapter(emptyList())
    private lateinit var bt_deleted: ImageButton
    private lateinit var addnewstudent: Button


    private val studentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_student -> {

//           inputD().apply {
//
//               setTargetFragment(this@StudentListFragment,0)
//               show(this@StudentListFragment.requireFragmentManager(),"input")
//           }
            // studentViewModel.addnewstudent(UUID.randomUUID(),77,"OMAR",false)


                true


            }
            else -> return super.onOptionsItemSelected(item)
        }
    }




    // TODO: Rename and change types of parameters








    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {







        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

       add=view.findViewById(R.id.floatingActionButton)
        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(context)



        add.setOnClickListener {

            inputD().apply {

                setTargetFragment(this@StudentListFragment,0)
                show(this@StudentListFragment.requireFragmentManager(),"input")
            }
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentViewModel.studentListLiveData .observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got stu ${crimes.size}")
                    updateUI(crimes)
                }
            })
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val st_number: TextView = itemView.findViewById(R.id.Student_number_tv)
        val st_name: TextView = itemView.findViewById(R.id.Student_name_tv)
        val st_passed: TextView = itemView.findViewById(R.id.Student_isPassed_tv)
        val bt_del: ImageButton = itemView.findViewById(R.id.bt_deleted) as ImageButton
        private lateinit var student: Student
        fun bind(item: Student) {

            this.student = item
            st_number.text = this.student.number.toString()
            st_name.text = this.student.name
            if (this.student.passed)
                st_passed.text = "passed "
            else
                st_passed.text = "faild "

        }


        init {
           bt_del.setOnClickListener {
               val builder=AlertDialog.Builder(requireContext())
               builder.setPositiveButton("yes"){_,_->

                   studentViewModel.deletUser(student)
               }

               builder.setNegativeButton("no"){_,_->


               }
               builder.setTitle("Deleted ${student.name}")
               builder.setMessage("Are you sure you want to delete")
               builder.create().show()

            }
        }
    }




    private inner class StudentAdapter(var students: List<Student>) :
        RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.studentlist, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)


        }

        override fun getItemCount(): Int {
            return students.size

        }



    }

    private fun updateUI(students: List<Student>) {

        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StudentListFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.studentbar, menu)
    }

    override fun addnewstudent(student: Student) {
        studentViewModel.addstudent(student)

    }

    fun onStudentDeleted(students: List<Student>){



    }
}



