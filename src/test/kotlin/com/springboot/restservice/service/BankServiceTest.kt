package com.springboot.restservice.service

import com.springboot.restservice.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its datasource to retrieve banks once`() {
        // when
        bankService.getBanks()

        // then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}