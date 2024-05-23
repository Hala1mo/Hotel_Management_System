package dto;

import lombok.Data;

@Data
public class customerDTO {

    private Long id;
    private String firstName;

    private String lastName;
    private String phone_number;

    private String email;
}
