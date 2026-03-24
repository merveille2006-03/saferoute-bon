package com.saferoute.service;

import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.LocationRepository;
import com.saferoute.domain.repository.SafeZoneRepository;
import com.saferoute.domain.repository.SettingsRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class LocationTrackingService_MembersInjector implements MembersInjector<LocationTrackingService> {
  private final Provider<LocationRepository> locationRepositoryProvider;

  private final Provider<SafeZoneRepository> safeZoneRepositoryProvider;

  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public LocationTrackingService_MembersInjector(
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.locationRepositoryProvider = locationRepositoryProvider;
    this.safeZoneRepositoryProvider = safeZoneRepositoryProvider;
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  public static MembersInjector<LocationTrackingService> create(
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<SafeZoneRepository> safeZoneRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new LocationTrackingService_MembersInjector(locationRepositoryProvider, safeZoneRepositoryProvider, alertRepositoryProvider, settingsRepositoryProvider);
  }

  @Override
  public void injectMembers(LocationTrackingService instance) {
    injectLocationRepository(instance, locationRepositoryProvider.get());
    injectSafeZoneRepository(instance, safeZoneRepositoryProvider.get());
    injectAlertRepository(instance, alertRepositoryProvider.get());
    injectSettingsRepository(instance, settingsRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.saferoute.service.LocationTrackingService.locationRepository")
  public static void injectLocationRepository(LocationTrackingService instance,
      LocationRepository locationRepository) {
    instance.locationRepository = locationRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.LocationTrackingService.safeZoneRepository")
  public static void injectSafeZoneRepository(LocationTrackingService instance,
      SafeZoneRepository safeZoneRepository) {
    instance.safeZoneRepository = safeZoneRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.LocationTrackingService.alertRepository")
  public static void injectAlertRepository(LocationTrackingService instance,
      AlertRepository alertRepository) {
    instance.alertRepository = alertRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.LocationTrackingService.settingsRepository")
  public static void injectSettingsRepository(LocationTrackingService instance,
      SettingsRepository settingsRepository) {
    instance.settingsRepository = settingsRepository;
  }
}
