package itau.iti.notification.central.notificationcentral.dto

import itau.iti.notification.central.notificationcentral.model.NotificationStatus
import java.time.LocalDateTime

data class NotificationView(
    val notificationId: Long,
    val notificationTypeTitle: String,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime,
    val status: NotificationStatus
)
