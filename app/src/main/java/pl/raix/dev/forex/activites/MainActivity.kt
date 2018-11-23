package pl.raix.dev.forex.activites

import HttpManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.raix.dev.forex.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HttpManager.getInstance(this.applicationContext)

        supportFragmentManager.beginTransaction().add(android.R.id.content, MainFragment()).commit()
    }

}
