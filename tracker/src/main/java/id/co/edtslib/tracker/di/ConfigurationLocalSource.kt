package id.co.edtslib.tracker.di

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.edtslib.tracker.data.Configuration
import id.co.edtslib.tracker.data.TrackerApps

class ConfigurationLocalSource(sharedPreferences: SharedPreferences, app: Application): LocalDataSource<Configuration>(sharedPreferences) {
    private var configuration: Configuration? = null

    override fun getKeyName(): String = "configuration"
    override fun getValue(json: String): Configuration = Gson().fromJson(json, object : TypeToken<Configuration>() {}.type)

    val apps = TrackerApps.create(app.applicationContext)

    override fun save(data: Configuration?) {
        configuration = data
        super.save(data)
    }

    override fun getCached(): Configuration? {
        return if (configuration != null) {
            configuration
        } else {
            super.getCached()
        }
    }

    override fun clear() {
        configuration = null
        super.clear()
    }

    fun getSessionId() = getCached()?.sessionId
    fun getUserId(): Long {
        val configuration = getCached()
        return if (configuration?.userId == null) 0L else configuration.userId
    }
}