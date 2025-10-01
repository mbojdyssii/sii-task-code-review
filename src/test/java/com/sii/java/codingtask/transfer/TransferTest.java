package com.sii.java.codingtask.transfer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.sii.java.codingtask.user.NewUser;
import com.sii.java.codingtask.user.UserController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransferTest {

    private static final String TEST_FIRST_NAME = "testFirstName";
    private static final String TEST_LAST_NAME = "testLastName";
    private static final Double MILLION_DOUBLE = 1000000D;
    private static final int MILLION_INT = 1000;
    private static final int THREAD_POOL_SIZE = 200;

    private long fromUserId;
    private long toUserId;

    @Autowired
    private UserController userController;

    @Autowired
    private TransferService transferService;

    @BeforeEach
    public void setUp() {
        fromUserId = userController.createUser(new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, MILLION_DOUBLE));
        toUserId = userController.createUser(new NewUser(TEST_FIRST_NAME, TEST_LAST_NAME, MILLION_DOUBLE));
    }

    @Test
    public void shouldExecuteMillionTransfers() {
        IntStream.range(0, MILLION_INT).forEach(value -> transferService.doTransfer(
                new Transfer(fromUserId, toUserId, 1D)));

        assertThat(userController.getUser(fromUserId).getBalance()).isEqualTo(MILLION_DOUBLE - MILLION_INT);
        assertThat(userController.getUser(toUserId).getBalance()).isEqualTo(MILLION_DOUBLE + MILLION_INT);
    }

    @Test
    @Disabled
    public void shouldExecuteMillionTransfersIn200Threads() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        Collection<Future<?>> futures = new ArrayList<>(MILLION_INT);
        for (int t = 0; t < MILLION_INT; t++) {
            futures.add(service.submit(() -> transferService.doTransfer(
                    new Transfer(fromUserId, toUserId, 1D))));
        }
        for (Future<?> f : futures) {
            f.get();
        }

        assertThat(userController.getUser(fromUserId).getBalance()).isEqualTo(MILLION_DOUBLE - MILLION_INT);
        assertThat(userController.getUser(toUserId).getBalance()).isEqualTo(MILLION_DOUBLE + MILLION_INT);
    }

    @Test
    @Disabled
    public void shouldExecuteMillionTransfersInParallelStream() {
        IntStream.range(0, 10).parallel().forEach(
                value -> {
                    if (value % 2 == 0) {
                        transferService.doTransfer(new Transfer(fromUserId, toUserId, 1D));
                    } else {
                        transferService.doTransfer(new Transfer(toUserId, fromUserId, 1D));
                    }
                }
        );

        assertThat(userController.getUser(fromUserId).getBalance()).isEqualTo(MILLION_DOUBLE);
        assertThat(userController.getUser(toUserId).getBalance()).isEqualTo(MILLION_DOUBLE);
    }
}

