package com.saferoute.presentation.viewmodels;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<Application> applicationProvider;

  public AuthViewModel_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(applicationProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<Application> applicationProvider) {
    return new AuthViewModel_Factory(applicationProvider);
  }

  public static AuthViewModel newInstance(Application application) {
    return new AuthViewModel(application);
  }
}
