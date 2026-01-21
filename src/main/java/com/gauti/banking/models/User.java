package com.gauti.banking.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User extends AbstractEntity implements UserDetails {

    private String firstname; 

    private String lastname;
    
    @Column(unique = true)
    private String email; 

    private String password; 

    private boolean active; 

    @OneToOne(mappedBy="user")
    private Address address; 

    @OneToMany(mappedBy="user")
    private List<Transaction> transactions; 

    @OneToMany(mappedBy="user")
    private List<Contact> contact;

    @OneToOne(mappedBy="user")
    private Account account; 

    @OneToOne(mappedBy="user")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collection.singletonList(new SimpleGrantedAuthority(role.getName()));
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
      return active;
    }
}
