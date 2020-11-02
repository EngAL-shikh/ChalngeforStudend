package com.example.chalngeforcrime

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ThemedSpinnerAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import java.util.*
import javax.security.auth.callback.Callback

class inputD:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v=activity?.layoutInflater?.inflate(R.layout.costumdialog,null)

        val name=v?.findViewById(R.id.name) as EditText
        val num=v?.findViewById(R.id.num) as EditText
        val pass=v?.findViewById(R.id.pass) as CheckBox

        return  AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("ADD"){dialog,_->
                val s=Student(UUID.randomUUID(),num.text.toString().toInt(),name.text.toString(),pass.isChecked)
                targetFragment.let {fragment ->
                    (fragment as Callbacks).addnewstudent(s)
                }
            }.setNegativeButton("cancel"){dialog,_->
                dialog.cancel()
            }.create()




    }

    interface Callbacks{

        fun addnewstudent(student: Student)
    }
}