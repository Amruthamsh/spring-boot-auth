package com.example.movie_app.model;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String director;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    private Integer rating;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Movie() {

    }


    public Movie(Long ID, String title, String director, Genre genre, Integer releaseYear, Integer rating) {
        this.ID = ID;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre=" + genre +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                '}';
    }
}

