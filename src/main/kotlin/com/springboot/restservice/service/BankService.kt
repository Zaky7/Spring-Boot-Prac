package com.springboot.restservice.service

import com.springboot.restservice.datasource.BankDataSource
import com.springboot.restservice.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}