package com.tad.musicplayer.nowplaying;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tad.musicplayer.models.Song;
import com.tad.musicplayer.utils.TimberUtils;
import com.tad.musicplayer.widgets.CircleImageView;
import com.tad.musicplayer.MusicPlayer;
import com.tad.musicplayer.MusicService;
import com.tad.musicplayer.R;
import com.tad.musicplayer.dataloaders.SongLoader;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;


public class Timber6 extends BaseNowplayingFragment {

    TextView nextSong;
    CircleImageView nextArt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_timber6, container, false);

        setMusicStateListener();
        setSongDetails(rootView);

        initGestures(rootView.findViewById(R.id.album_art));

        ((SeekBar) rootView.findViewById(R.id.song_progress)).getProgressDrawable().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY));
        ((SeekBar) rootView.findViewById(R.id.song_progress)).getThumb().setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP));

        nextSong = (TextView) rootView.findViewById(R.id.title_next);
        nextArt = (CircleImageView) rootView.findViewById(R.id.album_art_next);

        rootView.findViewById(R.id.nextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicPlayer.next();
            }
        });

        return rootView;
    }

    @Override
    public void updateShuffleState() {
        if (shuffle != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.SHUFFLE)
                    .setSizeDp(30);

            if (MusicPlayer.getShuffleMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(accentColor);

            shuffle.setImageDrawable(builder.build());
            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MusicPlayer.cycleShuffle();
                    updateShuffleState();
                    updateRepeatState();
                }
            });
        }
    }

    @Override
    public void updateRepeatState() {
        if (repeat != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setSizeDp(30);

            if (MusicPlayer.getRepeatMode() == 0) {
                builder.setColor(Color.WHITE);
            } else builder.setColor(accentColor);

            if (MusicPlayer.getRepeatMode() == MusicService.REPEAT_NONE) {
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT);
                builder.setColor(Color.WHITE);
            } else if (MusicPlayer.getRepeatMode() == MusicService.REPEAT_CURRENT) {
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT_ONCE);
                builder.setColor(accentColor);
            } else if (MusicPlayer.getRepeatMode() == MusicService.REPEAT_ALL) {
                builder.setColor(accentColor);
                builder.setIcon(MaterialDrawableBuilder.IconValue.REPEAT);
            }

            repeat.setImageDrawable(builder.build());
            repeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MusicPlayer.cycleRepeat();
                    updateRepeatState();
                    updateShuffleState();
                }
            });
        }
    }

    @Override
    public void onMetaChanged() {
        super.onMetaChanged();
        if (getActivity() != null) {
            long nextId = MusicPlayer.getNextAudioId();
            Song next = SongLoader.getSongForID(getActivity(), nextId);
            nextSong.setText(next.title);
            nextArt.setImageURI(TimberUtils.getAlbumArtUri(next.albumId));
        }
    }
}
