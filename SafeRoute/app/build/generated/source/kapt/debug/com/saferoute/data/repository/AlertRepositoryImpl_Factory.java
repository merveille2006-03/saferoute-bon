package com.saferoute.data.repository;

import android.content.Context;
import com.saferoute.data.local.dao.SOSAlertDao;
import com.saferoute.domain.repository.EmergencyContactRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AlertRepositoryImpl_Factory implements Factory<AlertRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<SOSAlertDao> sosAlertDaoProvider;

  private final Provider<EmergencyContactRepository> contactRepositoryProvider;

  public AlertRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<SOSAlertDao> sosAlertDaoProvider,
      Provider<EmergencyContactRepository> contactRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.sosAlertDaoProvider = sosAlertDaoProvider;
    this.contactRepositoryProvider = contactRepositoryProvider;
  }

  @Override
  public AlertRepositoryImpl get() {
    return newInstance(contextProvider.get(), sosAlertDaoProvider.get(), contactRepositoryProvider.get());
  }

  public static AlertRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<SOSAlertDao> sosAlertDaoProvider,
      Provider<EmergencyContactRepository> contactRepositoryProvider) {
    return new AlertRepositoryImpl_Factory(contextProvider, sosAlertDaoProvider, contactRepositoryProvider);
  }

  public static AlertRepositoryImpl newInstance(Context context, SOSAlertDao sosAlertDao,
      EmergencyContactRepository contactRepository) {
    return new AlertRepositoryImpl(context, sosAlertDao, contactRepository);
  }
}
