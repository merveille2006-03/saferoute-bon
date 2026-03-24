package com.saferoute.service;

import com.saferoute.domain.repository.AlertRepository;
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
public final class SOSAlertService_MembersInjector implements MembersInjector<SOSAlertService> {
  private final Provider<AlertRepository> alertRepositoryProvider;

  private final Provider<LocationRepository> locationRepositoryProvider;

  public SOSAlertService_MembersInjector(Provider<AlertRepository> alertRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider) {
    this.alertRepositoryProvider = alertRepositoryProvider;
    this.locationRepositoryProvider = locationRepositoryProvider;
  }

  public static MembersInjector<SOSAlertService> create(
      Provider<AlertRepository> alertRepositoryProvider,
      Provider<LocationRepository> locationRepositoryProvider) {
    return new SOSAlertService_MembersInjector(alertRepositoryProvider, locationRepositoryProvider);
  }

  @Override
  public void injectMembers(SOSAlertService instance) {
    injectAlertRepository(instance, alertRepositoryProvider.get());
    injectLocationRepository(instance, locationRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.saferoute.service.SOSAlertService.alertRepository")
  public static void injectAlertRepository(SOSAlertService instance,
      AlertRepository alertRepository) {
    instance.alertRepository = alertRepository;
  }

  @InjectedFieldSignature("com.saferoute.service.SOSAlertService.locationRepository")
  public static void injectLocationRepository(SOSAlertService instance,
      LocationRepository locationRepository) {
    instance.locationRepository = locationRepository;
  }
}
