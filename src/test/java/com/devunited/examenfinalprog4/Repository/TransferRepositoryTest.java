package com.devunited.examenfinalprog4.Repository;

import com.devunited.examenfinalprog4.model.Transfers;
import com.devunited.examenfinalprog4.repository.TransferRepository;
import com.devunited.examenfinalprog4.repository.TransferRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransferRepositoryTest {
    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private TransferRepositoryImpl transferRepositoryImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        List<Transfers> simulatedTransfer = new ArrayList<>();
        simulatedTransfer.add(new Transfers(1, 100.00, false, true,
                "BOA", "2345678901"));

        when(transferRepository.getAllTransfers()).thenReturn(simulatedTransfer);
    }


    @Test
    public void testGetAllTransfer_ReturnsListOfTransfer() throws SQLException {
        List<Transfers> simulatedTransfer = new ArrayList<>();
        simulatedTransfer.add(new Transfers(1, 100.00,  false, true,
                "BOA", "2345678901"));
        simulatedTransfer.add(new Transfers(2,  150.00, true, false,
                " ", "3456789012"));

        when(transferRepository.getAllTransfers()).thenReturn(simulatedTransfer);

        List<Transfers> actualTransfer = transferRepositoryImpl.getAllTransfers();

        assertThat(actualTransfer).hasSize(5);
    }

    @Test
    public void testGetTransferById_ValidId_ReturnsTransfer() throws SQLException {

        int transferId = 5;

        Transfers actualTransfer = transferRepositoryImpl.getTransferById(transferId);

        assertThat(actualTransfer).isNotNull();
        assertThat(actualTransfer.getId()).isEqualTo(transferId);
    }

    @Test
    public void testGetTransferById_InvalidId_ReturnsEmpty() throws SQLException {
        int invalidId = 100;

        Transfers actualTransfer = transferRepositoryImpl.getTransferById(invalidId);

        assertThat(actualTransfer).isNull();
    }

    @Test
    public void testCreateTransfer_ReturnsCreatedTransfer() {
        try {
            Transfers newTransfer = new Transfers();
            newTransfer.setId(6);
            newTransfer.setAmount(600.00);
            newTransfer.setSame_bank(true);
            newTransfer.setOther_bank(false);
            newTransfer.setOther_account_number("6789012345");

            when(transferRepository.createTransfer(any(Transfers.class)))
                    .thenAnswer(invocation -> {
                        Transfers argTransfer = invocation.getArgument(0);
                        return new Transfers(6, argTransfer.getAmount(),
                                argTransfer.isSame_bank(),argTransfer.isOther_bank(),
                                argTransfer.getOther_bank_name(), argTransfer.getOther_account_number());
                    });

            Transfers createdTransfer = transferRepositoryImpl.createTransfer(newTransfer);

            assertThat(createdTransfer).isNotNull();
            assertThat(createdTransfer.getId()).isPositive();
            assertThat(createdTransfer.getAmount()).isEqualTo(newTransfer.getAmount());
            assertThat(createdTransfer.getOther_bank_name()).isEqualTo(newTransfer.getOther_bank_name());
            assertThat(createdTransfer.isSame_bank()).isEqualTo(newTransfer.isSame_bank());
            assertThat(createdTransfer.isOther_bank()).isEqualTo(newTransfer.isOther_bank());
            assertThat(createdTransfer.getOther_account_number()).isEqualTo(newTransfer.getOther_account_number());
           } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testUpdateTransfer_ValidData_ReturnsUpdatedTransfer() throws SQLException {
        int transferIdToUpdate = 6;
        Transfers updatedTransfer = new Transfers(transferIdToUpdate, 600.00,
                true, false, " Other", "6789012345");

        when(transferRepository.updateTransfer(String.valueOf(transferIdToUpdate), updatedTransfer)).thenReturn(updatedTransfer);

        Transfers result = transferRepositoryImpl.updateTransfer(String.valueOf(transferIdToUpdate), updatedTransfer);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(transferIdToUpdate);
        assertThat(result.getAmount()).isEqualTo(updatedTransfer.getAmount());
        assertThat(result.isSame_bank()).isEqualTo(updatedTransfer.isSame_bank());
        assertThat(result.isOther_bank()).isEqualTo(updatedTransfer.isOther_bank());
        assertThat(result.getOther_bank_name()).isEqualTo(updatedTransfer.getOther_bank_name());
        assertThat(result.getOther_account_number()).isEqualTo(updatedTransfer.getOther_account_number());
    }


    @Test
    public void testUpdateTransfer_SQLException_ThrowsException() {
        try {
            int transferIdToUpdate = 6;
            Transfers updatedTransfer = new Transfers(transferIdToUpdate, 600.00,
                    true, false, " ", "6789012345");

            Transfers result = transferRepositoryImpl.updateTransfer(String.valueOf(transferIdToUpdate), updatedTransfer);

            assertThat(result).isNotNull();
            assertThat(result.getAmount()).isEqualTo(600.00);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
