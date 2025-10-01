package com.sii.java.codingtask.transfer;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sii.java.codingtask.mail.Mail;
import com.sii.java.codingtask.mail.MailService;
import com.sii.java.codingtask.user.UserEntity;
import com.sii.java.codingtask.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TransferService {

    private static final String SUPPORT_EMAIL = "support@test.com";
    private final TransferHistoryRepository transferHistoryRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private static final TransferHistoryMapper MAPPER = Mappers.getMapper(TransferHistoryMapper.class);


    @Transactional
    public Long doTransfer(final Transfer transfer) {
        TransferValidator.validate(transfer);
        log.debug("start transfer");

        UserEntity fromUser = userRepository.findById(transfer.getFromUserId())
                .orElseThrow(() -> new IllegalStateException("User from doesn't exist"));
        UserEntity toUser = userRepository.findById(transfer.getToUserId())
                .orElseThrow(() -> new IllegalStateException("User to doesn't exist"));

        if (fromUser.getBalance().compareTo(transfer.getAmount()) < 0) {
            throw new IllegalStateException("User doesn't have enough money");
        }
        TransferHistoryEntity transferHistory = new TransferHistoryEntity(fromUser, toUser, transfer.getAmount());
        log.debug("start transfer with id: " + transferHistory.getId());
        fromUser.setBalance(fromUser.getBalance() - transfer.getAmount());
        transferHistory.setStatus(TransferStatus.AMOUNT_TAKEN_FROM_USER);
        toUser.setBalance(toUser.getBalance() + transfer.getAmount());
        transferHistory.setStatus(TransferStatus.SUCCESS);
        log.debug("end transfer");
        transferHistory = transferHistoryRepository.save(transferHistory);
        mailService.sendMail(new Mail(SUPPORT_EMAIL, "Transfer", transferHistory.toString()));
        return transferHistory.getId();
    }

    public List<TransferHistory> getTransfers() {
        return transferHistoryRepository.findAll().stream()
                .map(MAPPER::entityToDomain)
                .collect(Collectors.toList());
    }
}
