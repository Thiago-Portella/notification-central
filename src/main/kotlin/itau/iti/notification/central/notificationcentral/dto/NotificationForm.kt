package itau.iti.notification.central.notificationcentral.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class NotificationForm(
    @NotNull
    val notificationTypeId: Long,
    @field:NotEmpty(message = "The field title cannot be empty")
    @field:Size(
        min = 5,
        max = 50,
        message = "The title field cannot be less than 5 characters and not more than 50 characters"
    )
    val title: String,
    @field:NotEmpty(message = "The field message cannot be empty")
    @field:Size(
        min = 10,
        max = 250,
        message = "The message field cannot be less than 10 characters and not more than 250 characters"
    )
    val message: String
)