package ru.vdh.todocompose.core.domain.exception

class UnknownDomainException(throwable: Throwable) : DomainException(throwable) {
    constructor(errorMessage: String) : this(Throwable(errorMessage))
}
