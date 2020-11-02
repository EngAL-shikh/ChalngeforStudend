package com.example.chalngeforcrime

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class StudentListFragment : Fragment(),inputD.Callbacks {


    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var bt_deleted: Button


    private val studentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_student -> {

           inputD().apply {

               setTargetFragment(this@StudentListFragment,0)
               show(this@StudentListFragment.requireFragmentManager(),"input")
           }
            // studentViewModel.addnewstudent(UUID.randomUUID(),77,"OMAR",false)










                true


            }
            else -> return super.onOptionsItemSelected(item)
        }
    }




    // TODO: Rename and change types of parameters



    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val st_number: TextView = itemView.findViewById(R.id.Student_number_tv)
        val st_name: TextView = itemView.findViewById(R.id.Student_name_tv)
        val st_passed: TextView = itemView.findViewById(R.id.Student_isPassed_tv)
        val bt_del: Button = itemView.findViewById(R.id.bt_deleted)
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
                onStudentDeleted(adapterPosition)
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
            return studentViewModel.Students.size

        }



    }

    private fun updateUI() {
        val students =
            studentViewModel.Students
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
        studentViewModel.addnewstudent(student)
        updateUI()
    }

    fun onStudentDeleted(item : Int){
        studentViewModel.deleted(item)
        updateUI()
    }
}



