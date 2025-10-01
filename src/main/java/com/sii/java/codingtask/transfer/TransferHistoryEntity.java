package com.sii.java.codingtask.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sii.java.codingtask.user.UserEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table(name = "transfer_history")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferHistoryEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_from")
    private UserEntity fromUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_to")
    private UserEntity toUser;

    private Double amount;

    private TransferStatus status;

    public TransferHistoryEntity(UserEntity fromUser, UserEntity toUser, Double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.status = TransferStatus.NEW;
    }
}
