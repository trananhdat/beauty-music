
package com.tad.musicplayer.lastfmapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LastfmArtist {

    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String SIMILAR = "similar";
    private static final String TAGS = "tags";
    private static final String BIO = "bio";

    @SerializedName(NAME)
    public String mName;

    @SerializedName(IMAGE)
    public List<Artwork> mArtwork;

    @SerializedName(SIMILAR)
    public SimilarArtist mSimilarArtist;

    @SerializedName(TAGS)
    public ArtistTag mArtistTags;

    @SerializedName(BIO)
    public ArtistBio mArtistBio;


    public class SimilarArtist {

        public static final String ARTIST = "artist";

        @SerializedName(ARTIST)
        public List<LastfmArtist> mSimilarArtist;
    }

    public class ArtistTag {

        public static final String TAG = "tag";

        @SerializedName(TAG)
        public List<com.tad.musicplayer.lastfmapi.models.ArtistTag> mTags;
    }


}
