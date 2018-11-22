package pl.raix.dev.forex.activites

import HttpManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init singleton
        HttpManager.getInstance(this.applicationContext).requestQueue
    }
}
