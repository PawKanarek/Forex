import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import pl.raix.dev.forex.api.GsonRequest
import pl.raix.dev.forex.data.HistoricalResponse
import java.text.SimpleDateFormat
import java.util.*

class HttpManager constructor(context: Context) {
    companion object {
        private const val API_ACCESS_KEY = "e57468f2c4a7ae2a2409a7e324edb627"
        private const val API_URL = "http://data.fixer.io/api/"
        private const val TAG = "HttpManager"

        @Volatile
        private var INSTANCE: HttpManager? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HttpManager(context).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    private fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun getHistorical(
        date: Date,
        listener: Response.Listener<HistoricalResponse>,
        errorListener: Response.ErrorListener
    ) {
        val url = "${HttpManager.API_URL}${dateParser(date)}?access_key=${HttpManager.API_ACCESS_KEY}"
        val gsonObjectRequest = GsonRequest(url, HistoricalResponse::class.java, null, listener, errorListener)

        addToRequestQueue(gsonObjectRequest)
    }

    private fun dateParser(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }
}