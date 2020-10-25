package com.example.mvvmpractice.ui.main.movie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvvmpractice.R;
import com.example.mvvmpractice.adapter.MovieAdapter;
import com.example.mvvmpractice.model.Movie;
import com.example.mvvmpractice.ui.splash.SplashFragment;
import com.example.mvvmpractice.ui.splash.SplashFragmentDirections;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar loading;

    @BindView(R.id.rv_movie)
    RecyclerView rv_movie;

    private MovieViewModel viewModel;
    private MovieAdapter adapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        showLoading(true);
        adapter = new MovieAdapter(getContext());

        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie.setAdapter(adapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(), observeViewModel);

    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if (movies != null){

            //set adapter
            //add adapter to recyclerview

                adapter.setListMovie(movies);
                adapter.notifyDataSetChanged();
                showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rv_movie.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rv_movie.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}