package com.springboot.restservice.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide collection of banks`() {
        // when
        val banks = mockBankDataSource.retrieveBanks()

        // then
        assertThat(banks).isNotEmpty
    }
    
    @Test
    fun `should provide some mock data`() {
        // when
        val banks = mockBankDataSource.retrieveBanks()
        
        // then
        assertThat(banks).allMatch { it.accountNum.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}