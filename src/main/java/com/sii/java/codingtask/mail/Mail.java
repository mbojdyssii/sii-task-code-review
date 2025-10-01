package com.sii.java.codingtask.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Mail {
    private final String to;
    private final String subject;
    private final String body;
}
