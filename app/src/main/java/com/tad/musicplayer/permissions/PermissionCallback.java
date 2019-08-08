
package com.tad.musicplayer.permissions;

public interface PermissionCallback {
    void permissionGranted();

    void permissionRefused();
}