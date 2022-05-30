package itau.iti.notification.central.notificationcentral.controller

import itau.iti.notification.central.notificationcentral.model.NotificationType
import itau.iti.notification.central.notificationcentral.service.NotificationTypeService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/type")
class TypeController(
    private val service: NotificationTypeService
) {
    @GetMapping
    fun listNotificationTypes(): List<NotificationType> {
        return service.listNotificationTypes()
    }

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): ResponseEntity<NotificationType> {
        val notificationType = service.searchById(id)
        return ResponseEntity.ok(notificationType)
    }

    @PostMapping
    fun createType(
        @RequestBody @Valid form: NotificationType,
        uriBuilder: UriComponentsBuilder
        ): ResponseEntity<NotificationType> {
        val notificationType = service.createType(form)
        val uri = uriBuilder.path("/type/${notificationType.typeId}").build().toUri()
        return ResponseEntity.created(uri).body(notificationType)
    }

    @PutMapping
    fun updateNotificationType(form: NotificationType): ResponseEntity<NotificationType> {
        val notificationType = service.updateNotificationType(form)
        return ResponseEntity.ok(notificationType)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteType(@PathVariable id: Long) {
        service.deleteById(id)
    }
}