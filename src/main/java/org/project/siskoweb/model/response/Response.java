package org.project.siskoweb.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int statusCode;
    private Object message;
    private T data;
    private int total;
}
