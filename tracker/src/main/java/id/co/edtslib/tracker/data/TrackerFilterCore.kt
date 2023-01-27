package id.co.edtslib.tracker.data

import com.google.gson.annotations.SerializedName
import id.co.edtslib.tracker.Tracker
import java.util.*

data class TrackerFilterCore (
    @SerializedName("event_name")
    val eventName: String,
    @SerializedName("event_timestamp")
    val eventTimeStamp: Long,
    @SerializedName("pageview_id")
    val pageViewId: String,
    @SerializedName("event_id")
    val eventId: Long,
    @SerializedName("event_category")
    val eventCategory: String,
    @SerializedName("page_name")
    val pageName: String,
    @SerializedName("filter_list")
    val list: List<String>
) {
    companion object {
        fun create(list: List<String>) =
            TrackerFilterCore(eventName = "user_filter",
                eventTimeStamp = Date().time,
                pageViewId = Tracker.currentPageId,
                eventId = Tracker.eventId++,
                eventCategory = "",
                pageName = Tracker.currentPageName,
                list = list)


    }

}