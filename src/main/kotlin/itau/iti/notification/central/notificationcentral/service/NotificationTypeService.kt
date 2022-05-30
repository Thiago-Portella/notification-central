package itau.iti.notification.central.notificationcentral.service

import itau.iti.notification.central.notificationcentral.exception.NotFoundException
import itau.iti.notification.central.notificationcentral.model.NotificationType
import org.springframework.stereotype.Service


@Service
class NotificationTypeService(
    private var types: List<NotificationType> = ArrayList(),
    private val notFoundMessage: String = "Type Not Found"
) {
    fun listNotificationTypes(): List<NotificationType> {
        return types
    }

    fun searchById(id: Long): NotificationType {
        return types.stream().filter {t ->
            t.typeId == id
        }.findFirst().orElseThrow {NotFoundException(notFoundMessage)}
    }


    fun createType(type: NotificationType): NotificationType {
        type.typeId = types.size.toLong() + 1
        types = types.plus(type)
        return type
    }

    fun updateNotificationType(form: NotificationType): NotificationType {
        val type = types.stream().filter { t ->
            t.typeId == form.typeId
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        val updatedNotificationType = NotificationType(
            typeId = form.typeId,
            title = form.title,
            description = form.description
        )
        types = types.minus(type).plus(updatedNotificationType)
        return updatedNotificationType
    }

    fun deleteById(id: Long) {
        val type = types.stream().filter { t ->
            t.typeId == id
        }.findFirst().orElseThrow{ NotFoundException(notFoundMessage) }
        types = types.minus(type)
    }
}