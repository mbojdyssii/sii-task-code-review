package com.sii.java.codingtask.transfer;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class TransferController {
    private static final String TRANSFERS = "/transfers";
    private final TransferService transferService;

    @GetMapping(TRANSFERS)
    public List<TransferHistory> getAllTransfers() {
        return transferService.getTransfers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(TRANSFERS)
    public Long doTransfer(final Transfer transfer) {
        return transferService.doTransfer(transfer);
    }

}
