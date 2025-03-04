package com.example.predict2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class Drawer : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home ->  {
                    Toast.makeText(this, "Medication", Toast.LENGTH_SHORT).show()
                    // Start the Prescription activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_medication -> {
                    Toast.makeText(this, "Medication", Toast.LENGTH_SHORT).show()
                    // Start the Prescription activity
                    val intent = Intent(this, ReportActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_appointment -> {
                    Toast.makeText(this, "Appointment", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AppointmentActivity::class.java))


                }
                R.id.nav_chat -> {
                    Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DoctorListActivity::class.java))

                    // Start the Prescription activity

                }
                R.id.nav_logout -> {
                    // Perform the logout action
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, Login::class.java))
                    finish()

                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
