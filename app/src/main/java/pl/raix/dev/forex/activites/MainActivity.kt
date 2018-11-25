package pl.raix.dev.forex.activites

import HttpManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import pl.raix.dev.forex.R
import pl.raix.dev.forex.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init singleton
        HttpManager.getInstance(this.applicationContext)
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
    }
}
