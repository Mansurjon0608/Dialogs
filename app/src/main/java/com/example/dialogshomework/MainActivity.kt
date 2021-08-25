package com.example.dialogshomework

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.connection_activity.view.*
import kotlinx.android.synthetic.main.custom_activity.*
import kotlinx.android.synthetic.main.custom_activity.view.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_alert.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle("Alert Dialog")
            alertDialog.setMessage("Here is info about alert dialog. Do you want to continue?")

            alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "Done", Toast.LENGTH_SHORT).show()
                }

            })

            alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(this@MainActivity, "Canceled", Toast.LENGTH_SHORT).show()
                }

            })

            alertDialog.setNeutralButton("Nothing...", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "Nothing, progres is canceled",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            alertDialog.show()

        }

        btn_custom.setOnClickListener {
            val customDialog = AlertDialog.Builder(this)
            val dialog = customDialog.create()

            dialog.setTitle("Custom Dialog")
            val dialogView = layoutInflater.inflate(R.layout.custom_activity, null, false)

            dialog.setView(dialogView)
            dialogView.btn_save.setOnClickListener {
                Toast.makeText(
                    this,
                    "${dialogView.edit_email.text} /n ${dialogView.edit_password.text}",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
            dialog.show()

        }

        btn_fragment.setOnClickListener {
            val dialogFragment = MyDialogFragment.newInstance("First", "Second")
            dialogFragment.show(supportFragmentManager.beginTransaction(), "keys")
        }

        btn_datapicker.setOnClickListener {
            val datePichkerDialog = DatePickerDialog(this)

            datePichkerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "${dayOfMonth}/${month - 1}/$year", Toast.LENGTH_SHORT).show()

            }
            datePichkerDialog.show()
        }


        btn_timepicker.setOnClickListener {
            val timePickerDialog =
                TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, 24, 60, true)
            timePickerDialog.updateTime(12, 24)
            timePickerDialog.show()
        }

        btn_bottomsheet.setOnClickListener {
            val bottumSheet = BottomSheetDialog(this)
            bottumSheet.setContentView(
                layoutInflater.inflate(
                    R.layout.bottomsheet_activity,
                    null,
                    false
                )
            )

            bottumSheet.show()
        }

        btn_snackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Hello Snackbar", Snackbar.LENGTH_SHORT)

            snackbar.setAction("Click", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
                }

            })
            snackbar.show()
        }

        btn_connection.setOnClickListener {
            val customDialog = AlertDialog.Builder(this)
            val dialog = customDialog.create()



            val dialogView = layoutInflater.inflate(R.layout.connection_activity, null, false )
            dialogView.txt_connection.text = "No internet connection. Check your connection or try again"
            dialog.window?.setBackgroundDrawableResource(R.drawable.margin)
            dialog.setView(dialogView)
            dialogView.btn_cancel.setOnClickListener {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialogView.btn_retry.setOnClickListener {

                dialog.dismiss()

                dialog.setView(dialogView)
            }
                dialog.show()
        }


    }
}