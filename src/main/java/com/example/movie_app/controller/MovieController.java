package com.example.movie_app.controller;

import com.example.movie_app.dto.ApiResponse;
import com.example.movie_app.model.Movie;
import com.example.movie_app.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/admin")
    public String doMoviesProtected(){
        return "Da Movies!!! You are an admin wooo";
    }

    @GetMapping("")
    public ApiResponse<List<Movie>> allMovies(){
        ApiResponse<List<Movie>> apiResponse = new ApiResponse<>(200, "Fetched All movies", movieService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse).getBody();
    }

    @GetMapping("{id}")
    public ApiResponse<Movie> getMovie(@PathVariable Long id){
        ApiResponse<Movie> fetchedMovie = new ApiResponse<>(200, "Fetched movie of Id: " + id, movieService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(fetchedMovie).getBody();
    }

    @PostMapping("")
    public ApiResponse<Movie> createMovie(@RequestBody Movie movie){
        ApiResponse<Movie> apiResponse = new ApiResponse<>(201, "Movie created successfully", movieService.saveMovie(movie));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse).getBody();
    }

    @PutMapping("{id}")
    public ApiResponse<Movie> editMovie(@PathVariable Long id, @RequestBody Movie movie){
        ApiResponse<Movie> apiResponse = new ApiResponse<>(200, "Movie of Id: "+id+" edited successfully",  movieService.editMovie(movie, id));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse).getBody();
    }

    @DeleteMapping("{id}")
    public ApiResponse<Object> deleteMovie(@PathVariable Long id){
        movieService.deleteByMovieId(id);
        return new ApiResponse<>(200,"Movie of id: "+ id + " successfully deleted",null);
    }
}
