package com.springboot.restservice.datasource.mock

import com.springboot.restservice.datasource.BankDataSource
import com.springboot.restservice.model.Bank
import org.springframework.stereotype.Repository


@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 0.0, 1),
        Bank("1010", 17.0, 1),
        Bank("4560", 21.3, 0)
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank = banks.firstOrNull() { it.accountNumber == accountNumber }
        ?: throw NoSuchElementException("Could not found a bank with Account Number $accountNumber")

    override fun createBank(newBank: Bank): Bank {
        if(banks.any { it.accountNumber == newBank.accountNumber }) {
            throw IllegalArgumentException("Bank Account number ${newBank.accountNumber} already exist")
        }

        banks.add(newBank)
        return newBank
    }
}