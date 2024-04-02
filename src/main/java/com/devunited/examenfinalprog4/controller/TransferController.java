package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Transfers;
import com.devunited.examenfinalprog4.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public List<Transfers> getAllTransfers() throws SQLException {
        return transferService.getAllTransfers();
    }

    @GetMapping("/transfers/{id}")
    public Transfers getTransferById(@PathVariable int id) throws SQLException {
        return transferService.getTransferById(id);
    }


    @PostMapping("/transfers")
    public Transfers addTransfer(@RequestBody Transfers transfer) throws SQLException {
        return transferService.createTransfer(transfer);
    }

    @PutMapping("/transfers/{id}")
    public Transfers updateTransfer(@PathVariable int id, @RequestBody Transfers transfer) throws SQLException {
        return transferService.updateTransfer(id, transfer);
    }
    @PutMapping("/transfers/cancel/{id}")
    public ResponseEntity<String> cancelTransfer(@PathVariable int id) throws SQLException {
        boolean success = transferService.cancelTransfer(id);
        if (success) {
            return ResponseEntity.ok("Transfer cancelled successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to cancel transfer");
        }
    }
}
