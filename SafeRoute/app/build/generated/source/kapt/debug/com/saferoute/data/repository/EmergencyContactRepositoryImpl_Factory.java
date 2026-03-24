package com.saferoute.data.repository;

import com.saferoute.data.local.dao.EmergencyContactDao;
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
public final class EmergencyContactRepositoryImpl_Factory implements Factory<EmergencyContactRepositoryImpl> {
  private final Provider<EmergencyContactDao> contactDaoProvider;

  public EmergencyContactRepositoryImpl_Factory(Provider<EmergencyContactDao> contactDaoProvider) {
    this.contactDaoProvider = contactDaoProvider;
  }

  @Override
  public EmergencyContactRepositoryImpl get() {
    return newInstance(contactDaoProvider.get());
  }

  public static EmergencyContactRepositoryImpl_Factory create(
      Provider<EmergencyContactDao> contactDaoProvider) {
    return new EmergencyContactRepositoryImpl_Factory(contactDaoProvider);
  }

  public static EmergencyContactRepositoryImpl newInstance(EmergencyContactDao contactDao) {
    return new EmergencyContactRepositoryImpl(contactDao);
  }
}
