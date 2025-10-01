package com.sii.java.codingtask.transfer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransferHistoryMapper {

    @Mapping(target = "userFromId", source = "fromUser.id")
    @Mapping(target = "userToId", source = "toUser.id")
    @Mapping(target = "transferId", source = "id")
    TransferHistory entityToDomain(TransferHistoryEntity entity);

}
