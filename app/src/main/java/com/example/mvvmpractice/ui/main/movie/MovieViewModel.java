package com.example.mvvmpractice.ui.main.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmpractice.model.Movie;
import com.example.mvvmpractice.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository repository;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovieCollection() {
        return repository.getMovieCollection();
    }

}
