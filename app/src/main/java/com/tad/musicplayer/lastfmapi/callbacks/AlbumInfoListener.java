
package com.tad.musicplayer.lastfmapi.callbacks;

import com.tad.musicplayer.lastfmapi.models.LastfmAlbum;

public interface AlbumInfoListener {

    void albumInfoSuccess(LastfmAlbum album);

    void albumInfoFailed();

}
