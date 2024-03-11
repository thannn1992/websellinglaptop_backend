package than.MK.weblaptop.backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Create constructor with all properties
@NoArgsConstructor // Create empty constructor
public class LoginRequest {

    private String username;
    private String password;
}
