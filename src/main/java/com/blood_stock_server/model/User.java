package com.blood_stock_server.model;

import com.blood_stock_server.business.repository.model.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;

@ApiModel(description = "Model of user data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User implements UserDetails {
    @ApiModelProperty(name = "The unique id of the user.",
            value = "when saving a new object, it is itself generated in the database.")
    private Long id;
    @ApiModelProperty(name = "User's first name")
    private String firstName;
    @ApiModelProperty(name = "User's last name")
    private String lastName;
    @ApiModelProperty(name = "User's email")
    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE
            , message = "Example of valid email \"name@gmail.com\"")
    private String email;
    @ApiModelProperty(name = "User's password")
    private String password;
    @ApiModelProperty(name = "User's role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
