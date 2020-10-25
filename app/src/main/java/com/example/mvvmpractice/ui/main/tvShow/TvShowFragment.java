package com.example.mvvmpractice.ui.main.tvShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmpractice.R;
import com.example.mvvmpractice.adapter.TvShowAdapter;
import com.example.mvvmpractice.model.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TvShowFragment extends Fragment {

    @BindView(R.id.rv_tvshow)
    RecyclerView rv_tvShow;

    private TvShowViewModel viewModel;
    private TvShowAdapter adapter;

    public TvShowFragment() {

    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new TvShowAdapter(getContext());

        rv_tvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tvShow.setAdapter(adapter);

        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
        if (tvShows != null){
            //set adapter
            adapter.setListTvShow(tvShows);
            adapter.notifyDataSetChanged();
            //add adapter to recycler view
        }
    };
}