package com.sii.java.codingtask.transfer;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sii.java.codingtask.mail.MailService;
import com.sii.java.codingtask.user.UserEntity;
import com.sii.java.codingtask.user.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class TransferServiceTest {

    private static final Double TEST_USER_BALANCE = 10D;
    private static final String TEST_LAST_NAME = "testLastName";
    private static final String TEST_FIRST_NAME = "testFirstName";

    private TransferService transferService;
    private TransferHistoryRepository transferHistoryRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        transferHistoryRepository = mock(TransferHistoryRepository.class);
        MailService mailService = mock(MailService.class);
        transferService = new TransferService(transferHistoryRepository, userRepository, mailService);
    }

    @Test
    public void test1() {
        UserEntity userFrom = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        UserEntity userTo = new UserEntity(2L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userFrom));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userTo));
        when(transferHistoryRepository.save(any())).thenReturn(
                new TransferHistoryEntity(1L, userFrom, userTo, 2D, TransferStatus.SUCCESS));

        Long id = transferService.doTransfer(new Transfer(1L, 2L, 2D));

        verify(transferHistoryRepository).save(any());
        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void shouldThrowExceptionBecauseTransferAmountIsBiggerThanUserBalance() {
        UserEntity userFrom = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        UserEntity userTo = new UserEntity(2L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userFrom));
        when(userRepository.findById(2L)).thenReturn(Optional.of(userTo));

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> transferService.doTransfer(new Transfer(1L, 2L, 2 * TEST_USER_BALANCE)));
    }

    @Test
    public void shouldThrownExceptionBecauseOneUserDontExist() {
        UserEntity userFrom = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userFrom));

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> transferService.doTransfer(new Transfer(1L, 2L, 2D)));
    }

    @Test
    public void test2() {
        UserEntity userFrom = new UserEntity(1L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        UserEntity userTo = new UserEntity(2L, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_USER_BALANCE);
        TransferHistoryEntity transferHistory
                = new TransferHistoryEntity(1L, userFrom, userTo, 2D, TransferStatus.SUCCESS);
        when(transferHistoryRepository.findAll()).thenReturn(List.of(transferHistory));

        List<TransferHistory> transfers = transferService.getTransfers();

        TransferHistory transferHistoryExpected
                = new TransferHistory(1L, userFrom.getId(), userTo.getId(), 2D, TransferStatus.SUCCESS);
        assertThat(transfers).containsExactly(transferHistoryExpected);
    }
}
