package id.co.edtslib.tracker

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.asLiveData
import id.co.edtslib.tracker.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class Tracker private constructor(): KoinComponent {
    private val trackerViewModel: TrackerViewModel? by inject()

    companion object {
        private var tracker: Tracker? = null
        var baseUrl = ""
        var token = ""

        fun init(application: Application, baseUrl: String, token: String) {
            Tracker.baseUrl = baseUrl
            Tracker.token = token
            startKoin {
                androidContext(application.applicationContext)
                modules(
                    listOf(
                        networkingModule,
                        sharedPreferencesModule,
                        mainAppModule,
                        repositoryModule,
                        interactorModule,
                        viewModule
                    )
                )
            }

            if (tracker == null) {
                tracker = Tracker()
            }
            tracker?.trackerViewModel?.createSession()?.observeForever {
                tracker?.trackerViewModel?.trackStartApplication()?.observeForever {  }
            }


            application
                .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

                    }

                    override fun onActivityStarted(activity: Activity) {

                    }

                    override fun onActivityResumed(activity: Activity) {
                    }

                    override fun onActivityPaused(activity: Activity) {

                    }

                    override fun onActivityStopped(activity: Activity) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                    }

                    override fun onActivityDestroyed(activity: Activity) {

                    }
                })
        }

        fun setUserId(userId: Long) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.setUserId(userId)?.observeForever {  }
        }

        fun trackExitApplication() {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackExitApplication()?.observeForever {  }
        }


        fun trackPage(screenName: String) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackPage(screenName)?.observeForever {  }
        }

        fun trackPageDetail(name: String, detail: Any?) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackPageDetail(name, detail)?.observeForever {  }
        }

        fun trackClick(name: String) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackClick(name)?.observeForever {  }
        }

        fun trackFilters(name: String, filters: List<String>) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackFilters(name, filters)?.observeForever {  }

        }

        fun trackSort(name: String, sortType: String) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackSort(name, sortType)?.observeForever {  }
        }

        fun trackSubmissionSuccess(name: String) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackSubmission(name, true, "")?.observeForever {  }

        }

        fun trackSubmissionFailed(name: String, reason: String?) {
            if (tracker == null) {
                tracker = Tracker()
            }

            tracker?.trackerViewModel?.trackSubmission(name, false, reason)?.observeForever {  }

        }
    }
}