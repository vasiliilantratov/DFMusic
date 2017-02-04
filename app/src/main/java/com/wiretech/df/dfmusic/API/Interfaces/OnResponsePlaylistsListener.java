package com.wiretech.df.dfmusic.API.Interfaces;

import com.wiretech.df.dfmusic.API.Classes.PlayList;
import com.wiretech.df.dfmusic.API.Classes.PlayListResponse;

import java.util.List;

public interface OnResponsePlaylistsListener {
    void onResponse(PlayListResponse playListResponse);

    void onError();
}
