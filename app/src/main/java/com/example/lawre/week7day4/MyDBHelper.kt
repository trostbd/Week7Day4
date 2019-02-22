package com.example.lawre.week7day4

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lawre.week7day4.DatabaseConstants.DATABASE_NAME
import com.example.lawre.week7day4.DatabaseConstants.DATABASE_VERSION
import com.example.lawre.week7day4.DatabaseConstants.FIELD_CITY
import com.example.lawre.week7day4.DatabaseConstants.FIELD_DOB
import com.example.lawre.week7day4.DatabaseConstants.FIELD_GPA
import com.example.lawre.week7day4.DatabaseConstants.FIELD_MAJOR
import com.example.lawre.week7day4.DatabaseConstants.FIELD_MINOR
import com.example.lawre.week7day4.DatabaseConstants.FIELD_NAME
import com.example.lawre.week7day4.DatabaseConstants.FIELD_SSN
import com.example.lawre.week7day4.DatabaseConstants.FIELD_STATE
import com.example.lawre.week7day4.DatabaseConstants.TABLE_NAME
import java.util.ArrayList

class MySQLHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val allStudents: ArrayList<Student>?
        get() {
            val myDB = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME"
            val myCur = myDB.rawQuery(query, null)
            if (myCur.moveToFirst()) {
                val studentList = ArrayList<Student>()
                do {
                    val name = myCur.getString(myCur.getColumnIndex(FIELD_NAME))
                    val major = myCur.getString(myCur.getColumnIndex(FIELD_MAJOR))
                    val minor = myCur.getString(myCur.getColumnIndex(FIELD_MINOR))
                    val gpa = myCur.getString(myCur.getColumnIndex(FIELD_GPA))
                    val dob = myCur.getString(myCur.getColumnIndex(FIELD_DOB))
                    val city = myCur.getString(myCur.getColumnIndex(FIELD_CITY))
                    val state = myCur.getString(myCur.getColumnIndex(FIELD_STATE))
                    val ssn = myCur.getString(myCur.getColumnIndex(FIELD_SSN))
                    studentList.add(Student(name, major, minor, gpa, dob, city, state, ssn))
                } while (myCur.moveToNext())
                myCur.close()
                return studentList
            } else {
                myCur.close()
                return null
            }
        }

    override fun onCreate(db: SQLiteDatabase) {
        val createQuery = ("CREATE TABLE " + TABLE_NAME + "(" + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY)")
        db.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    fun insertStudent(stu: Student?) {
        val db = writableDatabase
        val conVal = ContentValues()
        if (stu != null) {
            conVal.put(FIELD_NAME, stu.name)
            conVal.put(FIELD_MAJOR, stu.major)
            conVal.put(FIELD_MINOR, stu.minor)
            conVal.put(FIELD_GPA, stu.gpa)
            conVal.put(FIELD_DOB, stu.dob)
            conVal.put(FIELD_CITY, stu.homeCity)
            conVal.put(FIELD_STATE, stu.homeState)
            conVal.put(FIELD_SSN, stu.ssn)

            db.insert(TABLE_NAME, null, conVal)
        }
    }

    fun updatePerson(stu: Student?): Int {
        if (stu != null) {
            val whereClause = FIELD_SSN + " = \"" + stu.ssn + "\""
            val db = this.writableDatabase
            val conVal = ContentValues()
            conVal.put(FIELD_NAME, stu.name)
            conVal.put(FIELD_MAJOR, stu.major)
            conVal.put(FIELD_MINOR, stu.minor)
            conVal.put(FIELD_GPA, stu.gpa)
            conVal.put(FIELD_DOB, stu.dob)
            conVal.put(FIELD_CITY, stu.homeCity)
            conVal.put(FIELD_STATE, stu.homeState)
            conVal.put(FIELD_SSN, stu.ssn)

            db.insert(TABLE_NAME, null, conVal)

            return db.update(TABLE_NAME, conVal, whereClause, null)
        } else
            return -1
    }

    fun deleteStudent(passedSsn: String): Int {
        val whereClause = FIELD_SSN + " = \"" + passedSsn + "\""
        val myDB = this.writableDatabase
        return myDB.delete(TABLE_NAME, whereClause, null)
    }

    fun getStudent(passedSsn: String?): Student? {
        var returnStudent: Student? = null
        if (passedSsn != null && !passedSsn.isEmpty()) {
            val myDB = this.readableDatabase
            val query = "SELECT * FROM $TABLE_NAME WHERE $FIELD_SSN = \"$passedSsn\""
            val myCur = myDB.rawQuery(query, null)
            if (myCur.moveToFirst()) {
                val name = myCur.getString(myCur.getColumnIndex(FIELD_NAME))
                val major = myCur.getString(myCur.getColumnIndex(FIELD_MAJOR))
                val minor = myCur.getString(myCur.getColumnIndex(FIELD_MINOR))
                val gpa = myCur.getString(myCur.getColumnIndex(FIELD_GPA))
                val dob = myCur.getString(myCur.getColumnIndex(FIELD_DOB))
                val city = myCur.getString(myCur.getColumnIndex(FIELD_CITY))
                val state = myCur.getString(myCur.getColumnIndex(FIELD_STATE))
                val ssn = myCur.getString(myCur.getColumnIndex(FIELD_SSN))
                returnStudent = Student(name, major, minor, gpa, dob, city, state, ssn)
            }
            myCur.close()
        }
        return returnStudent
    }
}