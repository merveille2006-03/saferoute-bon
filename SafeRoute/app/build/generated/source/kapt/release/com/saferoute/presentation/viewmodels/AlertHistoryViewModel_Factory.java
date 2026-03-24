package com.saferoute.presentation.viewmodels;

import com.saferoute.domain.repository.AlertRepository;
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
public final class AlertHistoryViewModel_Factory implements Factory<AlertHistoryViewModel> {
  private final Provider<AlertRepository> alertRepositoryProvider;

  public AlertHistoryViewModel_Factory(Provider<AlertRepository> alertRepositoryProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
  }

  @Override
  public AlertHistoryViewModel get() {
    return newInstance(alertRepositoryProvider.get());
  }

  public static AlertHistoryViewModel_Factory create(
      Provider<AlertRepository> alertRepositoryProvider) {
    return new AlertHistoryViewModel_Factory(alertRepositoryProvider);
  }

  public static AlertHistoryViewModel newInstance(AlertRepository alertRepository) {
    return new AlertHistoryViewModel(alertRepository);
  }
}
