package com.saferoute.di;

import com.saferoute.data.local.dao.SafeZoneDao;
import com.saferoute.data.local.database.SafeRouteDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideSafeZoneDaoFactory implements Factory<SafeZoneDao> {
  private final Provider<SafeRouteDatabase> databaseProvider;

  public DatabaseModule_ProvideSafeZoneDaoFactory(Provider<SafeRouteDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SafeZoneDao get() {
    return provideSafeZoneDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSafeZoneDaoFactory create(
      Provider<SafeRouteDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSafeZoneDaoFactory(databaseProvider);
  }

  public static SafeZoneDao provideSafeZoneDao(SafeRouteDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSafeZoneDao(database));
  }
}
