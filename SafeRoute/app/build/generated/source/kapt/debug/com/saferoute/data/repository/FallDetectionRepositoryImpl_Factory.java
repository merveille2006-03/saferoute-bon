package com.saferoute.data.repository;

import android.content.Context;
import com.saferoute.data.local.dao.FallEventDao;
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
public final class FallDetectionRepositoryImpl_Factory implements Factory<FallDetectionRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<FallEventDao> fallEventDaoProvider;

  public FallDetectionRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<FallEventDao> fallEventDaoProvider) {
    this.contextProvider = contextProvider;
    this.fallEventDaoProvider = fallEventDaoProvider;
  }

  @Override
  public FallDetectionRepositoryImpl get() {
    return newInstance(contextProvider.get(), fallEventDaoProvider.get());
  }

  public static FallDetectionRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<FallEventDao> fallEventDaoProvider) {
    return new FallDetectionRepositoryImpl_Factory(contextProvider, fallEventDaoProvider);
  }

  public static FallDetectionRepositoryImpl newInstance(Context context,
      FallEventDao fallEventDao) {
    return new FallDetectionRepositoryImpl(context, fallEventDao);
  }
}
