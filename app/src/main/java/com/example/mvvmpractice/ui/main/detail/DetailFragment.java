package com.example.mvvmpractice.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvvmpractice.R;
import com.example.mvvmpractice.model.Genre;
import com.example.mvvmpractice.model.Movie;
import com.example.mvvmpractice.model.TvShow;
import com.example.mvvmpractice.ui.MainActivity;
import com.example.mvvmpractice.ui.splash.SplashFragmentDirections;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    @BindView(R.id.img_detail_background)
    ImageView detailCover;

    @BindView(R.id.img_detail_poster)
    ImageView detailPoster;

    @BindView(R.id.detail_title)
    TextView tvTitle;

    @BindView(R.id.detail_genre)
    TextView tvGenre;

    @BindView(R.id.detail_description)
    TextView tvDesc;

    @BindView(R.id.detail_review)
    TextView tvVote;

    @BindView(R.id.rv_cast)
    RecyclerView rvCast;


    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel viewModel;
    private DetailCastAdapter adapter;
//    private MovieAdapter adapter;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rvCast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailCastAdapter(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvshow();

            if (movie != null) {
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }

        }
    }

    private void observeShowViewModel(int idShow) {
        viewModel.getTvShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        tvGenre.append(g.getName() + " | ");
                    } else {
                        tvGenre.append(g.getName());
                    }
                }
            }
        });

        viewModel.getShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rvCast.setAdapter(adapter);
            }
        });
    }
    private void observeMovieViewModel(int idMovie) {
        viewModel.getMovieGenre(idMovie).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        tvGenre.append(g.getName() + " | ");
                    } else {
                        tvGenre.append(g.getName());
                    }
                }
            }
        });
        viewModel.getMovieCast(idMovie).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rvCast.setAdapter(adapter);
            }
        });
    }

    private void initShow(TvShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(tvShow.getCover()).centerCrop().into(detailCover);
        Glide.with(getActivity()).load(tvShow.getPoster()).centerCrop().into(detailPoster);
        tvTitle.setText(tvShow.getTitle());
        tvVote.setText("(" + tvShow.getVote() + ")");
        tvDesc.setText(tvShow.getDescription());
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getCover()).into(detailCover);
        Glide.with(getActivity()).load(movie.getPoster()).into(detailPoster);
        tvTitle.setText(movie.getTitle());
        tvVote.setText(movie.getVote());
        tvDesc.setText(movie.getDescription());
    }
}