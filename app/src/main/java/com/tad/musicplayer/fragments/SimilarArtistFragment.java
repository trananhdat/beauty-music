
package com.tad.musicplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tad.musicplayer.models.Artist;
import com.tad.musicplayer.utils.Constants;
import com.tad.musicplayer.R;
import com.tad.musicplayer.dataloaders.ArtistLoader;
import com.tad.musicplayer.lastfmapi.LastFmClient;
import com.tad.musicplayer.lastfmapi.callbacks.ArtistInfoListener;
import com.tad.musicplayer.lastfmapi.models.ArtistQuery;
import com.tad.musicplayer.lastfmapi.models.LastfmArtist;

public class SimilarArtistFragment extends Fragment {

    private long artistID = -1;

    public static SimilarArtistFragment newInstance(long id) {
        SimilarArtistFragment fragment = new SimilarArtistFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.ARTIST_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artistID = getArguments().getLong(Constants.ARTIST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_similar_artists, container, false);

        Artist artist = ArtistLoader.getArtist(getActivity(), artistID);

        LastFmClient.getInstance(getActivity()).getArtistInfo(new ArtistQuery(artist.name), new ArtistInfoListener() {
            @Override
            public void artistInfoSucess(LastfmArtist artist) {

            }

            @Override
            public void artistInfoFailed() {
            }
        });

        return rootView;

    }

}
