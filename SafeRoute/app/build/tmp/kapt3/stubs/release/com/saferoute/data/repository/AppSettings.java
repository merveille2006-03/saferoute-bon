package com.saferoute.data.repository;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.saferoute.data.local.dao.FallEventDao;
import com.saferoute.data.local.entity.FallEventEntity;
import com.saferoute.domain.model.FallDetectionState;
import com.saferoute.domain.model.FallEvent;
import com.saferoute.domain.repository.FallDetectionRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.*;
import timber.log.Timber;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/saferoute/data/repository/AppSettings;", "", "()V", "FALL_THRESHOLD", "", "app_release"})
public final class AppSettings {
    public static final int FALL_THRESHOLD = 10;
    @org.jetbrains.annotations.NotNull()
    public static final com.saferoute.data.repository.AppSettings INSTANCE = null;
    
    private AppSettings() {
        super();
    }
}