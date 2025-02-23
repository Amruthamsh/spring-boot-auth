package com.example.movie_app.service;

import com.example.movie_app.exception.DatabaseErrorException;
import com.example.movie_app.exception.ResourceNotFoundException;
import com.example.movie_app.model.Movie;
import com.example.movie_app.respository.MovieRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        try{
            return movieRepository.save(movie);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseErrorException("Duplicate entry detected: " + e.getMostSpecificCause().getMessage());
        }catch (Exception e){
            throw new DatabaseErrorException("Error while saving movie to database");
        }
    }


    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Movie with ID " + id + " found"));
    }


    public Movie editMovie(Movie movie, Long id){
        Movie movieToBeChanged = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Movie with ID " + id + " found"));

        movieToBeChanged.setTitle(movie.getTitle());
        movieToBeChanged.setGenre(movie.getGenre());
        movieToBeChanged.setRating(movie.getRating());
        movieToBeChanged.setReleaseYear(movie.getReleaseYear());
        movieToBeChanged.setDirector(movie.getDirector());
        return movieRepository.save(movieToBeChanged);
    }

    public void deleteByMovieId(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("No Movie with ID " + id + " found");
        }
        movieRepository.deleteById(id);
    }
}
