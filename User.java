package com.example.demo.model;
import java.util.List;

// import org.hibernate.mapping.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;    

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "emailAddress", nullable = false, unique = true)
    private String emailAddress;

    @Column(name = "first_name", nullable = false, unique = false)
    private String first_name;

    @Column(name = "last_name", nullable = false, unique = false)
    private String last_name;

    @Column(name = "user_type", nullable = false, unique = false)
    private String user_type;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(name = "points_earned", nullable = true, unique = false)
    private int pointsEarned;
 
    @Column(name = "PFP", nullable = true, unique = false)
    private byte[] PFP;

    @Column(name = "bio", nullable = true, unique = false)
    private String bio;

    @OneToMany(mappedBy = "organizer")
    private Set<Event> organizedEvents;

    @ManyToMany(mappedBy = "volunteers")
    private Set<Event> events = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL) //used to track the rewards claimed by user
    private List<Rewards> rewardsReceived;

    //Getters and Setters of all Attributes
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public Set<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPFP() {
        return PFP;
    }

    public void setPFP(byte[] PFP) {
        this.PFP = PFP;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static class ChangeUsernameRequest {
        private String oldUsername;
        private String newUsername;

        public String getOldUsername() {
            return oldUsername;
        }

        public void setOldUsername(String oldUsername) {
            this.oldUsername = oldUsername;
        }

        public String getNewUsername() {
            return newUsername;
        }

        public void setNewUsername(String newUsername) {
            this.newUsername = newUsername;
        }
    }

    public static class ChangePasswordRequest {
        private String oldPassword;
        private String newPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    public static class ChangeBioRequest {
        private String newBio;
        private String oldUsername;

        public String getOldUsername() {
            return oldUsername;
        }

        public String getNewBio() {
            return newBio;
        }

        public void setNewBio(String newBio) {
            this.newBio = newBio;
        }
    }

    public static class ChangePFPRequest {
        private byte[] newPFP;
        private String oldUsername;

        public String getOldUsername() {
            return oldUsername;
        }

        public byte[] getNewPFP() {
            return newPFP;
        }

        public void setNewPFP(byte[] newPFP) {
            this.newPFP = newPFP;
        }
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public List<Rewards> getRewardsReceived() {
        return rewardsReceived;
    }

    public void setRewardsReceived(List<Rewards> rewardsReceived) {
        this.rewardsReceived = rewardsReceived;
    }



}
