package com.sii.java.codingtask.transfer;

import com.sii.java.codingtask.user.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-01T20:51:06+0200",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.jar, environment: Java 15.0.10 (Azul Systems, Inc.)"
)
public class TransferHistoryMapperImpl implements TransferHistoryMapper {

    @Override
    public TransferHistory entityToDomain(TransferHistoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TransferHistory transferHistory = new TransferHistory();

        transferHistory.setUserFromId( entityFromUserId( entity ) );
        transferHistory.setUserToId( entityToUserId( entity ) );
        transferHistory.setTransferId( entity.getId() );
        transferHistory.setAmount( entity.getAmount() );
        transferHistory.setStatus( entity.getStatus() );

        return transferHistory;
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
