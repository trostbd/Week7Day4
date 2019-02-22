package com.example.lawre.week7day4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.rvMainRecyclerView

data class Student(var name:String, var major:String, var minor:String, var gpa:String, var dob:String, var homeCity : String, var homeState : String, var ssn : String)

class MainActivity : AppCompatActivity()
{
    var db : MySQLHelper = MySQLHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMainRecyclerView.layoutManager = LinearLayoutManager(this)
        rvMainRecyclerView.adapter = RecyclerViewAdapter(ArrayList())
    }

    fun onClick(view : View)
    {
        var myIntent = Intent(this,UserInput::class.java)
        startActivity(myIntent)
    }
}
