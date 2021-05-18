package com.springboot.restservice.datasource.mock

import com.springboot.restservice.datasource.BankDataSource
import com.springboot.restservice.model.Bank
import org.springframework.stereotype.Repository


@Repository
class MockBankDataSource: BankDataSource {

    val banks = listOf(
        Bank("1234", 0.0, 1),
        Bank("1010", 17.0, 1),
        Bank("4560", 21.3, 0)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
}