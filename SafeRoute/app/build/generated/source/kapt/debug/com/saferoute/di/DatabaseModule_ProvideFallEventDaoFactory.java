package com.saferoute.di;

import com.saferoute.data.local.dao.FallEventDao;
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
public final class DatabaseModule_ProvideFallEventDaoFactory implements Factory<FallEventDao> {
  private final Provider<SafeRouteDatabase> databaseProvider;

  public DatabaseModule_ProvideFallEventDaoFactory(Provider<SafeRouteDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public FallEventDao get() {
    return provideFallEventDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideFallEventDaoFactory create(
      Provider<SafeRouteDatabase> databaseProvider) {
    return new DatabaseModule_ProvideFallEventDaoFactory(databaseProvider);
  }

  public static FallEventDao provideFallEventDao(SafeRouteDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideFallEventDao(database));
  }
}
