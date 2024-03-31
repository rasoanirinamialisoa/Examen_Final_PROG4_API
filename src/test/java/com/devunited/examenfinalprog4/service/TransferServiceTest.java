package com.devunited.examenfinalprog4.service;

import com.devunited.examenfinalprog4.model.Transfers;
import com.devunited.examenfinalprog4.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferServiceTest {
    private TransferService transferService;
    private TransferRepository transferRepository;

    @BeforeEach
    public void setUp() {
        transferRepository = mock(TransferRepository.class);
        transferService = new TransferServiceImpl(transferRepository);
    }

    @Test
    public void testGetAllTransfers_Success() throws SQLException {

        List<Transfers> expectedTransfer = new ArrayList<>();
        when(transferRepository.getAllTransfers()).thenReturn(expectedTransfer);

        List<Transfers> actualTransfer = transferService.getAllTransfers();

        assertEquals(expectedTransfer, actualTransfer);
    }

    @Test
    public void testGetTransferById_Success() throws SQLException {

        int transferId = 1;
        Transfers expectedTransfer = new Transfers(1, 100.00, false,
                true, "BOA", "2345678901");
        when(transferRepository.getTransferById(transferId)).thenReturn(expectedTransfer);

        Transfers actualTransfer = transferService.getTransferById(transferId);

        assertEquals(expectedTransfer, actualTransfer);
    }

    @Test
    public void testCreateTransfer_Success() throws SQLException {

        Transfers transfer = new Transfers(6, 350.00, true, false,
                " ", "7890123456");
        when(transferRepository.createTransfer(transfer)).thenReturn(transfer);

        Transfers createdTransfer = transferService.createTransfer(transfer);

        assertEquals(transfer, createdTransfer);
    }

    @Test
    public void testUpdateTransfer_Success() throws SQLException {

        int updateTransferId = 6;
        Transfers transfers = new Transfers(6, 350.00, true, false,
                " ", "7890123456");
        when(transferRepository.updateTransfer(String.valueOf(updateTransferId), transfers)).thenReturn(transfers);

        Transfers updatedTransfer = transferService.updateTransfer(String.valueOf(updateTransferId), transfers);

        assertEquals(transfers, updatedTransfer);
    }
}
