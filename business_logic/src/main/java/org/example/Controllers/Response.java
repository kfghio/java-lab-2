package org.example.Controllers;

import lombok.Data;
import org.example.Enums.Status;

@Data
public class Response<TClass> {
    private TClass resultClass;
    private Status status;
}
