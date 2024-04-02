package com.devunited.examenfinalprog4.controller;

import com.devunited.examenfinalprog4.model.Transfers;
import com.devunited.examenfinalprog4.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
public class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferService transferService;

    @Test
    public void testGetAllTransfers_Success() throws Exception {

        List<Transfers> transfers = new ArrayList<>();
        when(transferService.getAllTransfers()).thenReturn(transfers);

        mockMvc.perform(get("/api/transfers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetTransferById_Success() throws Exception {

        int transferId = 1;
        Transfers transfers = new Transfers(1, 100.00,  false, true,
                "BOA", "2345678901", false, true);
        when(transferService.getTransferById(transferId)).thenReturn(transfers);

        mockMvc.perform(get("/api/transfers/{id}", transferId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transfers.getId()));
    }

    @Test
    public void testAddTransfer_Success() throws Exception {

        Transfers transfers = new Transfers(4, 100.00,  false, true,
                "BOA", "2345678901", false, true);
        when(transferService.createTransfer(any(Transfers.class))).thenReturn(transfers);

        mockMvc.perform(post("/api/transfers")
                        .contentType("application/json")
                        .content("{\"id\":6 ,\"amount\":350.00,\"same_bank\":true, \"other_bank\": false, " +
                                "\"other_bank_name\" : \" \", \"other_account_number\" : \"7890123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transfers.getId()));
    }

    @Test
    public void testUpdateTransfer_Success() throws Exception {

        int updateTransferId = 6;
        Transfers transfers = new Transfers(1, 100.00,  false, true,
                "BOA", "2345678901", false, true);
        when(transferService.updateTransfer((eq(updateTransferId)), any(Transfers.class))).thenReturn(transfers);

        mockMvc.perform(put("/api/transfers/{id}", updateTransferId)
                        .contentType("application/json")
                        .content("{\"id\":6 ,\"amount\":350.00,\"same_bank\":true, \"other_bank\": false, " +
                                "\"other_bank_name\" : \" \", \"other_account_number\" : \"7890123456\"}"))
                .andExpect(status().isOk());
    }
}
