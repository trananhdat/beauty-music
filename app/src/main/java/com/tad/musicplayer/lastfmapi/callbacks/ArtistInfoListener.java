
package com.tad.musicplayer.lastfmapi.callbacks;

import com.tad.musicplayer.lastfmapi.models.LastfmArtist;

public interface ArtistInfoListener {

    void artistInfoSucess(LastfmArtist artist);

    void artistInfoFailed();

}
