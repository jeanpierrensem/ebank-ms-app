package com.jp.ebankservice.mcp;
import com.jp.ebankservice.dto.*;
import com.jp.ebankservice.services.*;
import jakarta.transaction.*;
import org.springframework.ai.mcp.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Component
@CrossOrigin(origins = "http://localhost:4200")
public class EbankMcpController {
    private BankAccountService bankAccountService;

    public EbankMcpController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @McpTool(description = "get all the bank account")
    public List<BankAccountResponseDto> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @McpTool(description = "get the bank account by the specified id ")
    public BankAccountResponseDto getBankAccountById(
            @McpArg(description = "the bank account id")
            @PathVariable String id) {
        return bankAccountService.getBankAccountById(id);
    }
    @McpTool(description = "get the bank account of the customer")
    public List<BankAccountResponseDto> getBankAccountByCustomerId(
            @McpArg(description = "the id of the customer")
            @PathVariable ("id") Long customerId) {
        return bankAccountService.getBankAccountByCustomerId(customerId);
    }
    @McpTool(description = "save the bank account")
    public BankAccountResponseDto saveBankAccount(
            @McpArg(description = "the bank account to be saved")
            @RequestBody BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.saveBankAccount(bankAccountRequestDto);
    }
    @McpTool(description = "update the bank account")
    public BankAccountResponseDto updateBankAccount(
            @McpArg(description = "the bank account to be updated")
            @RequestBody  BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.updateBankAccount(bankAccountRequestDto);
    }
    @McpTool(description = "delete the bank account")
    public void deleteBankAccountById(
            @McpArg(description = "the bank account id")
            @PathVariable String id) {
        bankAccountService.deleteBankAccountById(id);
    }
}