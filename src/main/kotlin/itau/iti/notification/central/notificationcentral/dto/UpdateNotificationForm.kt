package itau.iti.notification.central.notificationcentral.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class UpdateNotificationForm (
    @field:NotNull
    val notificationId: Long,
    @field:NotEmpty(message = "The message field cannot be empty")
    @field:Size(min = 10, max = 250, message = "The message field cannot be less than 10 characters and not more than 250 characters")
    val message: String,
)