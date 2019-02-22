package com.example.lawre.week7day4

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter(var studentList : ArrayList<Student>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item,p0,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvSsn.text = studentList[p1].ssn
        p0.tvName.text = studentList[p1].name
        p0.tvMajor.text = studentList[p1].major
        p0.tvMinor.text = studentList[p1].minor
        p0.tvGpa.text = studentList[p1].gpa
        p0.tvDob.text = studentList[p1].dob
        p0.tvCity.text = studentList[p1].homeCity
        p0.tvState.text = studentList[p1].homeState
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvSsn = view.tvSsn
        val tvName = view.tvName
        val tvMajor = view.tvMajor
        val tvMinor = view.tvMinor
        val tvGpa = view.tvGpa
        val tvDob = view.tvDob
        val tvCity = view.tvCity
        val tvState = view.tvState
    }
}