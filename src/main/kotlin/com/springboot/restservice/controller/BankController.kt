package com.springboot.restservice.controller

import com.springboot.restservice.datasource.BankDataSource
import com.springboot.restservice.model.Bank
import com.springboot.restservice.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/banks")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()
}