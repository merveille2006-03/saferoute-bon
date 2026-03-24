package com.saferoute.presentation.viewmodels;

import com.saferoute.domain.repository.EmergencyContactRepository;
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
public final class ContactsViewModel_Factory implements Factory<ContactsViewModel> {
  private final Provider<EmergencyContactRepository> contactRepositoryProvider;

  public ContactsViewModel_Factory(Provider<EmergencyContactRepository> contactRepositoryProvider) {
    this.contactRepositoryProvider = contactRepositoryProvider;
  }

  @Override
  public ContactsViewModel get() {
    return newInstance(contactRepositoryProvider.get());
  }

  public static ContactsViewModel_Factory create(
      Provider<EmergencyContactRepository> contactRepositoryProvider) {
    return new ContactsViewModel_Factory(contactRepositoryProvider);
  }

  public static ContactsViewModel newInstance(EmergencyContactRepository contactRepository) {
    return new ContactsViewModel(contactRepository);
  }
}
