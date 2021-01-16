package com.chalkboardexam.birthdays.businesslogic.domain.state

sealed class UIComponentType{

    class Toast: UIComponentType()

    class Dialog: UIComponentType()

    class None: UIComponentType()
}

data class Response(
    val message: String?,
    val uiComponentType: UIComponentType,
    val messageType: MessageType
)

data class StateMessage(val response: Response)


sealed class MessageType{

    class Success: MessageType()

    class Error: MessageType()

    class None: MessageType()
}

interface StateMessageCallback{

    fun removeMessageFromStack()
}
