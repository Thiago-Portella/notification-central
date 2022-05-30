package itau.iti.notification.central.notificationcentral.model

import java.time.LocalDateTime

data class Notification(
    var notificationId: Long? = null,
    val notificationType: NotificationType,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    var status: NotificationStatus = NotificationStatus.CREATED
)