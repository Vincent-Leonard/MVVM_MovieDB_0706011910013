package com.example.mvvmpractice.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmpractice.model.TvShow;
import com.example.mvvmpractice.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private TvShowRepository repository;

    public TvShowViewModel() {
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }
}
