package itau.iti.notification.central.notificationcentral.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class NotificationType(
    var typeId: Long? = null,
    @field:NotEmpty(message = "The title field cannot be empty")
    @field:Size(min = 5, max = 25, message = "The title field cannot be less than 5 and more than 25 characters")
    val title: String,
    @field:NotEmpty(message = "The field description cannot be empty")
    @field:Size(min = 10, max = 100, message = "The description field cannot be less than 10 and more than 100 characters")
    val description: String
)