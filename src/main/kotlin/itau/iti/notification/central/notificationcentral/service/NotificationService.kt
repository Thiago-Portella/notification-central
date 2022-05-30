package itau.iti.notification.central.notificationcentral.service

import itau.iti.notification.central.notificationcentral.dto.NotificationForm
import itau.iti.notification.central.notificationcentral.dto.NotificationView
import itau.iti.notification.central.notificationcentral.dto.UpdateNotificationForm
import itau.iti.notification.central.notificationcentral.exception.NotFoundException
import itau.iti.notification.central.notificationcentral.mapper.NotificationFormMapper
import itau.iti.notification.central.notificationcentral.mapper.NotificationViewMapper
import itau.iti.notification.central.notificationcentral.model.Notification
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class NotificationService(
    private var notifications: List<Notification> = ArrayList(),
    private val notificationViewMapper: NotificationViewMapper,
    private val notFoundMessage: String = "Notification Not Found"
) {

    fun listNotification(): List<NotificationView> {
        return notifications.stream().map { nv ->
            NotificationViewMapper().mapp(nv)
        }.collect(Collectors.toList())
    }

    fun searchById(id: Long): NotificationView {
        val view = notifications.stream().filter { notification ->
            notification.notificationId == id
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        return NotificationViewMapper().mapp(view)
    }

    fun createNotification(form: NotificationForm): NotificationView {
        val notification = NotificationFormMapper().mapp(form)
        notification.notificationId = notifications.size.toLong() + 1
        notifications = notifications.plus(notification)
        return NotificationViewMapper().mapp(notification)
    }

    fun updateNotification(form: UpdateNotificationForm): NotificationView {
        val notification = notifications.stream().filter { n ->
            n.notificationId == form.notificationId
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        val updatedNotification = Notification(
            notificationId = form.notificationId,
            notificationType = notification.notificationType,
            title = notification.title,
            message = form.message,
            creationDate = notification.creationDate,
            status = notification.status
        )
        notifications = notifications.minus(notification).plus(updatedNotification)
        return NotificationViewMapper().mapp(updatedNotification)
    }

    fun deleteById(id: Long) {
        val notification = notifications.stream().filter { n ->
            n.notificationId == id
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        notifications = notifications.minus(notification)
    }
}
