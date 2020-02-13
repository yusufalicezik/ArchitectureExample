package com.example.mdbclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mdbclient.R;
import com.example.mdbclient.databinding.MovieListItemBinding;
import com.example.mdbclient.model.Movie;
import com.example.mdbclient.view.MovieActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;
    //private ArrayList<Movie> movieArrayList; pagedListAdapter automatically

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
        //this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.movieListItemBinding.setMovie(movie);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(@NonNull  MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION) {

                        Movie selctedMovie = getItem(position);

                        Intent intent=new Intent(context, MovieActivity.class);
                        intent.putExtra("movie",selctedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
