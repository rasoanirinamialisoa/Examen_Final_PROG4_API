package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Transfers;

import java.sql.SQLException;
import java.util.List;

public interface TransferService {
    List<Transfers> getAllTransfers() throws SQLException;
    Transfers getTransferById(String id) throws SQLException;
    Transfers createTransfer(Transfers transfer) throws SQLException;
    Transfers updateTransfer(String id, Transfers transfer) throws SQLException;
}
