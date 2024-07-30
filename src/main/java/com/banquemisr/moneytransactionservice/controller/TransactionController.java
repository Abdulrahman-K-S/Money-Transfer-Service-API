package com.banquemisr.moneytransactionservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Transaction Controller", description = "The transaction controller endpoints")
public class TransactionController {
}
