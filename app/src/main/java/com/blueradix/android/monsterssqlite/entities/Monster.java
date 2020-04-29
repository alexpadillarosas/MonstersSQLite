package com.blueradix.android.monsterssqlite.entities;

import java.io.Serializable;

public class Monster implements Serializable {

    public static final String MONSTER_KEY = "monster_key";

    private Long id;
    private String name;
    private String description;
    private Long scariness;
    private String imageFileName;
    private Long votes;
    private Long stars;

    public Monster() {

    }

    public Monster(Long id, String name, String description, Long scariness, String imageFileName, Long votes, Long stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scariness = scariness;
        this.imageFileName = imageFileName;
        this.votes = votes;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getScariness() {
        return scariness;
    }

    public void setScariness(Long scariness) {
        this.scariness = scariness;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id= " + id + System.lineSeparator() +
                "name= " + name + System.lineSeparator() +
                "description= " + description + System.lineSeparator() +
                "scariness=" + scariness + System.lineSeparator() +
                "imageFileName= " + imageFileName + System.lineSeparator() +
                "votes= " + votes + System.lineSeparator() +
                "stars= " + stars + System.lineSeparator() +
                "}" + System.lineSeparator();
    }
}

