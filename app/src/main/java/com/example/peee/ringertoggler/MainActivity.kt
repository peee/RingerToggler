package com.example.peee.ringertoggler

import android.app.Activity
import android.app.NotificationManager
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (canAccessNotificationPolicy()) {
            getSystemService(AudioManager::class.java).apply {
                ringerMode = toggleRingerMode(ringerMode)
            }
            showToast(R.string.activity_main_toast_ringer_toggled)
        } else {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
            showToast(R.string.activity_main_toast_grant_notification_policy)
        }

        finish()
    }

    private fun canAccessNotificationPolicy() : Boolean =
            getSystemService(NotificationManager::class.java).isNotificationPolicyAccessGranted


    private fun toggleRingerMode(oldMode: Int) : Int =
            when (oldMode) {
                AudioManager.RINGER_MODE_NORMAL -> AudioManager.RINGER_MODE_VIBRATE
                else -> AudioManager.RINGER_MODE_NORMAL
            }

    private fun showToast(resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}
