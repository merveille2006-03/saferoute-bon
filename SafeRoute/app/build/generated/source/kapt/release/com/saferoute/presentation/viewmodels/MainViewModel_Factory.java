package com.saferoute.presentation.viewmodels;

import android.app.Application;
import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.EmergencyContactRepository;
import com.saferoute.domain.repository.FallDetectionRepository;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.domain.repository.SafeZoneRepository;
import com.saferoute.domain.repository.SettingsRepository;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<LocationRepository> locationRepositoryProvider;

  private final Provider<FallDetectionRepository> fallDetectionRepositoryProvider;

  private final Provider<SafeZoneRepository> safeZoneRepositoryProvider;

  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<EmergencyContactRepository> contactRepositoryProvider;

  public MainViewModel_Factory(Provider<Application> applicationProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider,
      Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<EmergencyContactRepository> contactRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.locationRepositoryProvider = locationRepositoryProvider;
    this.fallDetectionRepositoryProvider = fallDetectionRepositoryProvider;
    this.safeZoneRepositoryProvider = safeZoneRepositoryProvider;
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.contactRepositoryProvider = contactRepositoryProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(applicationProvider.get(), settingsRepositoryProvider.get(), locationRepositoryProvider.get(), fallDetectionRepositoryProvider.get(), safeZoneRepositoryProvider.get(), alertRepositoryProvider.get(), contactRepositoryProvider.get());
  }

  public static MainViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider,
      Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<EmergencyContactRepository> contactRepositoryProvider) {
    return new MainViewModel_Factory(applicationProvider, settingsRepositoryProvider, locationRepositoryProvider, fallDetectionRepositoryProvider, safeZoneRepositoryProvider, alertRepositoryProvider, contactRepositoryProvider);
  }

  public static MainViewModel newInstance(Application application,
      SettingsRepository settingsRepository, LocationRepository locationRepository,
      FallDetectionRepository fallDetectionRepository, SafeZoneRepository safeZoneRepository,
      AlertRepository alertRepository, EmergencyContactRepository contactRepository) {
    return new MainViewModel(application, settingsRepository, locationRepository, fallDetectionRepository, safeZoneRepository, alertRepository, contactRepository);
  }
}
