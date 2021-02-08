package com.dms.domain.outing.usecase

import com.dms.domain.base.UUIDUseCase
import com.dms.domain.outing.service.OutingService

class GetStudentUUIDUseCase(private val outingService: OutingService) : UUIDUseCase() {
    override fun getUUID(content: String): String =
        outingService.getStudentUUID()
}