package com.dms.domain.base

abstract class UUIDUseCase {

    abstract fun getUUID(content: String) : String

    fun execute(content: String){
        getUUID(content)
    }
}