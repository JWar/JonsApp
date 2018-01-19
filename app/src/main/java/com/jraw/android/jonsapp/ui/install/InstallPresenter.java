package com.jraw.android.jonsapp.ui.install;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 19/01/2018.
 * ...
 */

public class InstallPresenter implements InstallContract.PresenterInstall {
    private InstallContract.ViewInstall mViewInstall;
    public InstallPresenter(@NonNull InstallContract.ViewInstall aViewInstall) {
        mViewInstall = checkNotNull(aViewInstall);
    }
}
