package com.saferoute.presentation.viewmodels;

import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.domain.repository.SafeZoneRepository;
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
public final class SafeZonesViewModel_Factory implements Factory<SafeZonesViewModel> {
  private final Provider<SafeZoneRepository> safeZoneRepositoryProvider;

  private final Provider<LocationRepository> locationRepositoryProvider;

  public SafeZonesViewModel_Factory(Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider) {
    this.safeZoneRepositoryProvider = safeZoneRepositoryProvider;
    this.locationRepositoryProvider = locationRepositoryProvider;
  }

  @Override
  public SafeZonesViewModel get() {
    return newInstance(safeZoneRepositoryProvider.get(), locationRepositoryProvider.get());
  }

  public static SafeZonesViewModel_Factory create(
      Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider) {
    return new SafeZonesViewModel_Factory(safeZoneRepositoryProvider, locationRepositoryProvider);
  }

  public static SafeZonesViewModel newInstance(SafeZoneRepository safeZoneRepository,
      LocationRepository locationRepository) {
    return new SafeZonesViewModel(safeZoneRepository, locationRepository);
  }
}
