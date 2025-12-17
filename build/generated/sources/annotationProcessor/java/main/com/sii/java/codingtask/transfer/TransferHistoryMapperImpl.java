package com.sii.java.codingtask.transfer;

import com.sii.java.codingtask.transfer.TransferHistory.TransferHistoryBuilder;
import com.sii.java.codingtask.user.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T23:31:40+0100",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 21.0.9 (Amazon.com Inc.)"
)
public class TransferHistoryMapperImpl implements TransferHistoryMapper {

    @Override
    public TransferHistory entityToDomain(TransferHistoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TransferHistoryBuilder transferHistory = TransferHistory.builder();

        transferHistory.userFromId( entityFromUserId( entity ) );
        transferHistory.userToId( entityToUserId( entity ) );
        transferHistory.transferId( entity.getId() );
        transferHistory.amount( entity.getAmount() );
        transferHistory.status( entity.getStatus() );

        return transferHistory.build();
    }

    private Long entityFromUserId(TransferHistoryEntity transferHistoryEntity) {
        if ( transferHistoryEntity == null ) {
            return null;
        }
        UserEntity fromUser = transferHistoryEntity.getFromUser();
        if ( fromUser == null ) {
            return null;
        }
        Long id = fromUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long entityToUserId(TransferHistoryEntity transferHistoryEntity) {
        if ( transferHistoryEntity == null ) {
            return null;
        }
        UserEntity toUser = transferHistoryEntity.getToUser();
        if ( toUser == null ) {
            return null;
        }
        Long id = toUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
