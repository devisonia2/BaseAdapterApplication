package com.sonia.baseadapterapplication

import android.app.Dialog
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import baseadapterclass
import com.sonia.baseadapterapplication.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var array = arrayListOf("Black", "Red", "White")
    var baseadapterclass = baseadapterclass(array)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.listview?.adapter = baseadapterclass
        binding?.fab?.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.customdialog)
            dialog.show()
            var etName=dialog.findViewById<EditText>(R.id.etname)
            var btnAdd=dialog.findViewById<Button>(R.id.btnadd)

             btnAdd?.setOnClickListener {
                 if(etName?.text?.toString().isNullOrEmpty())
                     etName?.error="Enter Your Name "
                 else{
                     array.add(etName.text.toString())
                     baseadapterclass.notifyDataSetChanged()
                     dialog.dismiss()
                 }
             }
        }
        binding?.listview?.setOnItemClickListener { parent, view, position, id ->
            array.removeAt(position)
            baseadapterclass.notifyDataSetChanged()
        }

    }
}

