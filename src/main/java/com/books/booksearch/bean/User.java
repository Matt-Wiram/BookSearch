package com.books.booksearch.bean;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100, unique = true)
    private String username;

    @Column (nullable = false, length = 100, unique = true)
    private String email;

    @Column (nullable = false, length = 100)
    private String password;

    @Column (nullable = false, length = 254)
    private String address;

    @Lob
    @Column (nullable = true, length = 50000, columnDefinition = "BLOB")
    private byte [] profile_img;

    public User() {
    }

    public User(String username, String email, String password, String address, byte[] profile_img) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.profile_img = profile_img;
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.email = copy.email;
        this.password = copy.password;
        this.address = copy.address;
        this.profile_img = copy.profile_img;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(byte[] profile_img) {
        this.profile_img = profile_img;
    }
}
