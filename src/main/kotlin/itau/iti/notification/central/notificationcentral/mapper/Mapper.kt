package itau.iti.notification.central.notificationcentral.mapper

interface Mapper<Input, Output> {

    fun mapp(input: Input): Output
}