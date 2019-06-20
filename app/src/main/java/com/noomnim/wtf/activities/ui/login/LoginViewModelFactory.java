package com.noomnim.wtf.activities.ui.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.noomnim.wtf.activities.data.LoginDataSource;
import com.noomnim.wtf.activities.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom( LoginViewModel.class )) {
            return (T) new LoginViewModel( LoginRepository.getInstance( new LoginDataSource() ) );
        } else {
            throw new IllegalArgumentException( "Unknown ViewModel class" );
        }
    }
}
