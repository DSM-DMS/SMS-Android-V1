package com.dms.sms.feature.outing.dialog

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingStartDialogBinding
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import com.dms.sms.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingStartDialog : BaseDialog<FragmentOutingStartDialogBinding>() {
    override val viewModel by sharedViewModel<OutingAccessViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_outing_start_dialog

    val CHANNEL_ID = "OutingStartChannel"

    override fun observeEvent() {
        with(viewModel) {
            outingStartConfirmEvent.observe(viewLifecycleOwner, {
                startOrFinishOuting()
                getStudentUUID()
                clickOutingStartAlarm()
                dismiss()
            })

            outingStartCancelEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }
    }

    private fun clickOutingStartAlarm() {
        val notificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val fullScreenPendingIntent = PendingIntent.getActivity(requireContext(), 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)    // 2

        val mBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "OutingStartChannel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            notificationManager.createNotificationChannel(
                NotificationChannel(CHANNEL_ID, name, importance)
            )

            NotificationCompat.Builder(requireContext(), CHANNEL_ID)
        } else {
            NotificationCompat.Builder(requireContext())
        }

        mBuilder.apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("외출 시작 알림")
            setContentText("지금부터 외출이 시작됩니다.")
            setAutoCancel(true)
            setFullScreenIntent(fullScreenPendingIntent, true)
            priority = NotificationCompat.PRIORITY_HIGH
        }

        notificationManager.notify(1, mBuilder.build())
    }
}

