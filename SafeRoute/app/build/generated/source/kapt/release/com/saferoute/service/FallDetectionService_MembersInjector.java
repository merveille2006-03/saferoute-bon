package com.saferoute.service;

import com.saferoute.domain.repository.AlertRepository;
import com.saferoute.domain.repository.FallDetectionRepository;
import com.saferoute.domain.repository.LocationRepository;
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
public final class FallDetectionService_MembersInjector implements MembersInjector<FallDetectionService> {
  private final Provider<FallDetectionRepository> fallDetectionRepositoryProvider;

  private final Provider<LocationRepository> locationRepositoryProvider;

  private final Provider<AlertRepository> alertRepositoryProvider;

  public FallDetectionService_MembersInjector(
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider) {
    this.fallDetectionRepositoryProvider = fallDetectionRepositoryProvider;
    this.locationRepositoryProvider = locationRepositoryProvider;
    this.alertRepositoryProvider = alertRepositoryProvider;
  }

  public static MembersInjector<FallDetectionService> create(
      Provider<FallDetectionRepository> fallDetectionRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider,
      Provider<AlertRepository> alertRepositoryProvider) {
    return new FallDetectionService_MembersInjector(fallDetectionRepositoryProvider, locationRepositoryProvider, alertRepositoryProvider);
  }

  @Override
  public void injectMembers(FallDetectionService instance) {
    injectFallDetectionRepository(instance, fallDetectionRepositoryProvider.get());
    injectLocationRepository(instance, locationRepositoryProvider.get());
    injectAlertRepository(instance, alertRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.saferoute.service.FallDetectionService.fallDetectionRepository")
  public static void injectFallDetectionRepository(FallDetectionService instance,
      FallDetectionRepository fallDetectionRepository) {
    instance.fallDetectionRepository = fallDetectionRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.FallDetectionService.locationRepository")
  public static void injectLocationRepository(FallDetectionService instance,
      LocationRepository locationRepository) {
    instance.locationRepository = locationRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.FallDetectionService.alertRepository")
  public static void injectAlertRepository(FallDetectionService instance,
      AlertRepository alertRepository) {
    instance.alertRepository = alertRepository;
  }
}
