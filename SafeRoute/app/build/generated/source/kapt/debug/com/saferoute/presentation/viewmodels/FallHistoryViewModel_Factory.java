package com.saferoute.presentation.viewmodels;

import com.saferoute.domain.repository.FallDetectionRepository;
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
public final class FallHistoryViewModel_Factory implements Factory<FallHistoryViewModel> {
  private final Provider<FallDetectionRepository> fallDetectionRepositoryProvider;

  public FallHistoryViewModel_Factory(
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider) {
    this.fallDetectionRepositoryProvider = fallDetectionRepositoryProvider;
  }

  @Override
  public FallHistoryViewModel get() {
    return newInstance(fallDetectionRepositoryProvider.get());
  }

  public static FallHistoryViewModel_Factory create(
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider) {
    return new FallHistoryViewModel_Factory(fallDetectionRepositoryProvider);
  }

  public static FallHistoryViewModel newInstance(FallDetectionRepository fallDetectionRepository) {
    return new FallHistoryViewModel(fallDetectionRepository);
  }
}
