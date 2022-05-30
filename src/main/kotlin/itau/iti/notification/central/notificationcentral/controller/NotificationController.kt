package itau.iti.notification.central.notificationcentral.controller

import itau.iti.notification.central.notificationcentral.dto.NotificationForm
import itau.iti.notification.central.notificationcentral.dto.NotificationView
import itau.iti.notification.central.notificationcentral.dto.UpdateNotificationForm
import itau.iti.notification.central.notificationcentral.service.NotificationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val service: NotificationService
) {
    @GetMapping
    fun listNotification(): List<NotificationView> {
        return service.listNotification()
    }

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): ResponseEntity<NotificationView> {
        val notificationView = service.searchById(id)
        return ResponseEntity.ok(notificationView)
    }
    @PostMapping
    fun creatNotification(
        @RequestBody @Valid form: NotificationForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<NotificationView> {
        val notificationView = service.createNotification(form)
        val uri = uriBuilder.path("/notification/${notificationView.notificationId}").build().toUri()
        return ResponseEntity.created(uri).body(notificationView)
    }

    @PutMapping
    fun updateNotification(@RequestBody @Valid form: UpdateNotificationForm) : ResponseEntity<NotificationView> {
        val notificationView = service.updateNotification(form)
        return ResponseEntity.ok(notificationView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        service.deleteById(id)
    }
}