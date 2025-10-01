package com.sii.java.codingtask.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TransferHistory {
    private Long transferId;
    private Long userFromId;
    private Long userToId;
    private Double amount;
    private TransferStatus status;

}
