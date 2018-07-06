package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListHolderPattern> {
    private final List<Video> mVideoList;
    private final Context context;
    private final VideoItemClickListener movieInterface;

    public VideoListAdapter(List<Video> mVideoList, Context context, VideoItemClickListener movieInterface) {
        this.context = context;
        this.mVideoList = mVideoList;
        this.movieInterface = movieInterface;
    }

    @NonNull
    @Override
    public VideoListHolderPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_item, parent, false);

        return new VideoListHolderPattern(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListHolderPattern holder, int position) {
        Video video = mVideoList.get(position);
        holder.movieTitle.setText(video.getName());
        Glide
                .with(context)
                .load(video.getThumbNailUrl())
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class VideoListHolderPattern extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_movie)
        ImageView movieImage;
        @BindView(R.id.tv_movie_name)
        TextView movieTitle;


        VideoListHolderPattern(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            movieInterface.movieClick(position);
        }
    }


    public interface VideoItemClickListener {
        void movieClick(int position);
    }
}
