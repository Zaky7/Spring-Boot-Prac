package com.springboot.restservice.datasource

import com.springboot.restservice.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>

    fun retrieveBank(accountNumber: String): Bank

    fun createBank(newBank: Bank): Bank
}