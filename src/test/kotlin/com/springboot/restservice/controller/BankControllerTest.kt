package com.springboot.restservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springboot.restservice.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    private val baseUrl = "/api/banks"


    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all Banks`() {
            // when
            val response = mockMvc.get(baseUrl)

            // then
            response.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }

        }

    }

    @Nested
    @DisplayName("getBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with given account number`() {
            // given
            val accountNumber = "1234"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { MediaType.APPLICATION_JSON }
                    jsonPath("$.trust") { value("0.0") }
                    jsonPath("$.transactionFee") { value("1") }
                }
        }

        @Test
        fun `should return not found if Account Number does not exist`() {
            // given
            val accountNumber = "does_not_exist"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                    content { MediaType.APPLICATION_JSON }
                }
        }
    }

    @Nested
    @DisplayName("addBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should create a new account`() {
            // given
            val newBank = Bank("acc123", 31.45, 2)

            // when
            val response = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            // then
            response
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("acc123")}
                    jsonPath("$.trust") { value(31.45 )}
                    jsonPath("$.transactionFee") { value(2) }
                }
        }
        
        @Test
        fun `should return bad request when account already present`() {
            // given
            val duplicateBank = Bank("acc123", 31.45, 2)


            // when
            val response = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(duplicateBank)
            }

            // then
            response
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
            
        }
    }

}