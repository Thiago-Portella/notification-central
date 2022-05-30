package itau.iti.notification.central.notificationcentral.mapper

import itau.iti.notification.central.notificationcentral.dto.NotificationView
import itau.iti.notification.central.notificationcentral.model.Notification
import org.springframework.stereotype.Component

@Component
class NotificationViewMapper : Mapper<Notification, NotificationView> {
    override fun mapp(input: Notification): NotificationView {
        return NotificationView(
            notificationId = input.notificationId!!,
            notificationTypeTitle = input.notificationType.title,
            title = input.title,
            message = input.message,
            creationDate = input.creationDate,
            status = input.status
        )
    }
}