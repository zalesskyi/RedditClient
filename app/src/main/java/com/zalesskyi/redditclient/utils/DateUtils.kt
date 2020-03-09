package com.zalesskyi.redditclient.utils

import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

object DateUtils {

    private const val INC_DURATION = 1L

    private const val MS_IN_SEC = 1000L

    /**
     * Get formatted date.
     *
     * @param timestamp date timestamp
     *
     * @return time diff with 'now' in hours.
     */
    fun getFormattedDate(timestamp: Long) =
        DateTime(timestamp * MS_IN_SEC).getHoursDiffWithNow().toString()

    private fun DateTime.getHoursDiffWithNow() =
        DateTime.now().minus(millis).millis / TimeUnit.HOURS.toMillis(INC_DURATION)

}