package com.example.lawre.week7day4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_input.*

class UserInput : AppCompatActivity() {
    internal var switchToMain = false
    var myDBHelp = MySQLHelper(this)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_input)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btSelect -> if (etSsn.text != null && !etSsn.text.toString().isEmpty()) {
                val retrievedStudent = myDBHelp.getStudent(etSsn.text.toString())
                etSsn.setText(retrievedStudent?.ssn)
                etName.setText(retrievedStudent?.name)
                etMajor.setText(retrievedStudent?.major)
                etMinor.setText(retrievedStudent?.minor)
                etGpa.setText(retrievedStudent?.gpa)
                etDob.setText(retrievedStudent?.dob)
                etCity.setText(retrievedStudent?.homeCity)
                etState.setText(retrievedStudent?.homeState)
            } else {
                Toast.makeText(this, "Invalid SSN", Toast.LENGTH_SHORT)
            }
            R.id.btInsert -> if (etSsn.text != null && !etSsn.text.toString().isEmpty()) {
                val insertStu = Student(
                    etName.text.toString(),
                    etMajor.text.toString(),
                    etMinor.text.toString(),
                    etGpa.text.toString(),
                    etDob.text.toString(),
                    etCity.text.toString(),
                    etState.text.toString(),
                    etSsn.text.toString()
                )
                myDBHelp.insertStudent(insertStu)
                switchToMain = true
            } else {
                Toast.makeText(this, "Student must have a SSN", Toast.LENGTH_SHORT)
            }
            R.id.btUpdate -> if (etSsn.text != null && !etSsn.text.toString().isEmpty()) {
                var updateStu = myDBHelp.getStudent(etSsn.text.toString()) ?: Student("","","","","","","","")
                val ssn = etSsn.text.toString()
                val name: String
                val major: String
                val minor: String
                val gpa: String
                val dob: String
                val city: String
                val state: String

                //checks to see if field is empty
                //if yes, use existing value
                //if no, use new value

                if (etName.text != null && !etName.text.toString().isEmpty()) {
                    name = etName.text.toString()
                } else {
                    name = updateStu.name
                }
                if (etMajor.text != null && !etMajor.text.toString().isEmpty()) {
                    major = etMajor.text.toString()
                } else {
                    major = updateStu.major
                }
                if (etMinor.text != null && !etMinor.text.toString().isEmpty()) {
                    minor = etMinor.text.toString()
                } else {
                    minor = updateStu.minor
                }
                if (etGpa.text != null && !etGpa.text.toString().isEmpty()) {
                    gpa = etGpa.text.toString()
                } else {
                    gpa = updateStu.gpa
                }
                if (etDob.text != null && !etDob.text.toString().isEmpty()) {
                    dob = etDob.text.toString()
                } else {
                    dob = updateStu.dob
                }
                if (etCity.text != null && !etCity.text.toString().isEmpty()) {
                    city = etCity.text.toString()
                } else {
                    city = updateStu.homeCity
                }
                if (etState.text != null && !etState.text.toString().isEmpty()) {
                    state = etState.text.toString()
                } else {
                    state = updateStu.homeState
                }
                updateStu = Student(name, major, minor, gpa, dob, city, state, ssn)
                myDBHelp.updatePerson(updateStu)
                switchToMain = true
            } else {
                Toast.makeText(this, "Invalid SSN", Toast.LENGTH_SHORT)
            }
            R.id.btDelete -> if (etSsn.text != null && !etSsn.text.toString().isEmpty()) {
                myDBHelp.deleteStudent(etSsn.text.toString())
                switchToMain = true
            } else {
                Toast.makeText(this, "Invalid SSN", Toast.LENGTH_SHORT).show()
            }
            else -> Log.d(TAG, "onClick: not a button")
        }
        if (switchToMain) {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }

    companion object {
        val TAG = "tag_user_input "
    }
}
