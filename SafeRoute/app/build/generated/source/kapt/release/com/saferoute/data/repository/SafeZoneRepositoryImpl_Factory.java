package com.saferoute.data.repository;

import com.saferoute.data.local.dao.SafeZoneDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class SafeZoneRepositoryImpl_Factory implements Factory<SafeZoneRepositoryImpl> {
  private final Provider<SafeZoneDao> safeZoneDaoProvider;

  public SafeZoneRepositoryImpl_Factory(Provider<SafeZoneDao> safeZoneDaoProvider) {
    this.safeZoneDaoProvider = safeZoneDaoProvider;
  }

  @Override
  public SafeZoneRepositoryImpl get() {
    return newInstance(safeZoneDaoProvider.get());
  }

  public static SafeZoneRepositoryImpl_Factory create(Provider<SafeZoneDao> safeZoneDaoProvider) {
    return new SafeZoneRepositoryImpl_Factory(safeZoneDaoProvider);
  }

  public static SafeZoneRepositoryImpl newInstance(SafeZoneDao safeZoneDao) {
    return new SafeZoneRepositoryImpl(safeZoneDao);
  }
}
