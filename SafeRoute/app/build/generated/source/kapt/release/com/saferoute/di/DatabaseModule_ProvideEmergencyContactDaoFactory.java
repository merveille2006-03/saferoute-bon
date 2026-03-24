package com.saferoute.di;

import com.saferoute.data.local.dao.EmergencyContactDao;
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
public final class DatabaseModule_ProvideEmergencyContactDaoFactory implements Factory<EmergencyContactDao> {
  private final Provider<SafeRouteDatabase> databaseProvider;

  public DatabaseModule_ProvideEmergencyContactDaoFactory(
      Provider<SafeRouteDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public EmergencyContactDao get() {
    return provideEmergencyContactDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideEmergencyContactDaoFactory create(
      Provider<SafeRouteDatabase> databaseProvider) {
    return new DatabaseModule_ProvideEmergencyContactDaoFactory(databaseProvider);
  }

  public static EmergencyContactDao provideEmergencyContactDao(SafeRouteDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEmergencyContactDao(database));
  }
}
