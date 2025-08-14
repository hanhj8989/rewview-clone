package kr.co.rewview.clone.util

import android.os.SystemClock
import android.view.View

/**
 * A [View.OnClickListener] that debounces multiple clicks posted in the same frame.
 */
abstract class DebouncingOnClickListener : View.OnClickListener {

    private var lastClickTime: Long = 0L

    override fun onClick(v: View) {
        val currentClickTime = SystemClock.uptimeMillis()
        val elapsedTime = currentClickTime - lastClickTime
        lastClickTime = currentClickTime

        if (elapsedTime <= MIN_CLICK_INTERVAL) {
            return
        }

        doClick(v)
    }

    abstract fun doClick(v: View)

    companion object {
        private const val MIN_CLICK_INTERVAL = 600
    }
}
