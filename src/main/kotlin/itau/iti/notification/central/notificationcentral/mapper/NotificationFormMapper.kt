package itau.iti.notification.central.notificationcentral.mapper

import itau.iti.notification.central.notificationcentral.dto.NotificationForm
import itau.iti.notification.central.notificationcentral.model.Notification
import itau.iti.notification.central.notificationcentral.service.NotificationTypeService
import org.springframework.stereotype.Component

@Component
class NotificationFormMapper : Mapper<NotificationForm, Notification> {

    override fun mapp(input: NotificationForm): Notification {
        return Notification(
            notificationType = NotificationTypeService().searchById(input.notificationTypeId),
            title = input.title,
            message = input.message,
        )
    }
}