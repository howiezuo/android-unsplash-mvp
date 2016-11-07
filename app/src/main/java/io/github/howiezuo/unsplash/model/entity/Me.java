package io.github.howiezuo.unsplash.model.entity;

import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.realm.RealmObject;


public class Me extends RealmObject {

    private String id;
    private String username;
    private String name;
    private String bio;
    private String location;
    private int totalLikes;
    private int totalPhotos;
    private int totalCollections;
    private ProfileImage profileImage;

    public Me() {

    }

    public Me(UserDto dto) {
        setDto(dto);
    }

    public void setDto(UserDto dto) {
        id = dto.getId();
        username = dto.getUsername();
        name = dto.getName();
        bio = dto.getBio();
        location = dto.getLocation();
        totalLikes = dto.getTotalLikes();
        totalPhotos = dto.getTotalPhotos();
        totalCollections = dto.getTotalCollections();
        profileImage = new ProfileImage(dto.getProfileImage());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public int getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(int totalCollections) {
        this.totalCollections = totalCollections;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}
