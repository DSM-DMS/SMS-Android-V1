package com.dms.sms.feature.outing.dialog

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingFinishDialogBinding
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import com.dms.sms.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingFinishDialog : BaseDialog<FragmentOutingFinishDialogBinding>() {
    override val viewModel by sharedViewModel<OutingAccessViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_outing_finish_dialog

    val CHANNEL_ID = "OutingFinishChannel"

    override fun observeEvent() {
        with(viewModel) {
            outingFinishConfirmEvent.observe(viewLifecycleOwner, {
                dismiss()
                startOrFinishOuting()
                clickOutingFinishAlarm()
            })

            outingFinishCancelEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }

    }

    private fun clickOutingFinishAlarm() {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val fullScreenPendingIntent = PendingIntent.getActivity(requireContext(), 1,
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
            setContentTitle("외출 종료 알림")
            setContentText("선생님께 방문하여 최종 확인을 받아주세요. ")
            setAutoCancel(true)
            setFullScreenIntent(fullScreenPendingIntent, true)
            priority = NotificationCompat.PRIORITY_HIGH
        }

        notificationManager.notify(2, mBuilder.build())
    }

}