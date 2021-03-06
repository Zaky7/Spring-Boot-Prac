package com.springboot.restservice.service

import com.springboot.restservice.datasource.BankDataSource
import com.springboot.restservice.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)

    fun addBank(newBank: Bank): Bank = dataSource.createBank(newBank)

}