package com.saferoute.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.saferoute.data.local.entity.EmergencyContactEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EmergencyContactDao_Impl implements EmergencyContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EmergencyContactEntity> __insertionAdapterOfEmergencyContactEntity;

  private final EntityDeletionOrUpdateAdapter<EmergencyContactEntity> __deletionAdapterOfEmergencyContactEntity;

  private final EntityDeletionOrUpdateAdapter<EmergencyContactEntity> __updateAdapterOfEmergencyContactEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteContactById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllContacts;

  public EmergencyContactDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmergencyContactEntity = new EntityInsertionAdapter<EmergencyContactEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `emergency_contacts` (`id`,`userId`,`name`,`phoneNumber`,`email`,`relationship`,`priority`,`isEnabled`,`notifyOnFall`,`notifyOnSOS`,`notifyOnSafeZoneExit`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EmergencyContactEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhoneNumber());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmail());
        }
        if (entity.getRelationship() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRelationship());
        }
        statement.bindLong(7, entity.getPriority());
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.getNotifyOnFall() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        final int _tmp_2 = entity.getNotifyOnSOS() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        final int _tmp_3 = entity.getNotifyOnSafeZoneExit() ? 1 : 0;
        statement.bindLong(11, _tmp_3);
        statement.bindLong(12, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfEmergencyContactEntity = new EntityDeletionOrUpdateAdapter<EmergencyContactEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `emergency_contacts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EmergencyContactEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfEmergencyContactEntity = new EntityDeletionOrUpdateAdapter<EmergencyContactEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `emergency_contacts` SET `id` = ?,`userId` = ?,`name` = ?,`phoneNumber` = ?,`email` = ?,`relationship` = ?,`priority` = ?,`isEnabled` = ?,`notifyOnFall` = ?,`notifyOnSOS` = ?,`notifyOnSafeZoneExit` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EmergencyContactEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getPhoneNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPhoneNumber());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmail());
        }
        if (entity.getRelationship() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getRelationship());
        }
        statement.bindLong(7, entity.getPriority());
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.getNotifyOnFall() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        final int _tmp_2 = entity.getNotifyOnSOS() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        final int _tmp_3 = entity.getNotifyOnSafeZoneExit() ? 1 : 0;
        statement.bindLong(11, _tmp_3);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteContactById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM emergency_contacts WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllContacts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM emergency_contacts WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertContact(final EmergencyContactEntity contact,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfEmergencyContactEntity.insertAndReturnId(contact);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteContact(final EmergencyContactEntity contact,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfEmergencyContactEntity.handle(contact);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateContact(final EmergencyContactEntity contact,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEmergencyContactEntity.handle(contact);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteContactById(final long contactId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteContactById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, contactId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteContactById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllContacts(final String userId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllContacts.acquire();
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllContacts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllContacts(final String userId,
      final Continuation<? super List<EmergencyContactEntity>> $completion) {
    final String _sql = "SELECT * FROM emergency_contacts WHERE userId = ? ORDER BY priority ASC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EmergencyContactEntity>>() {
      @Override
      @NonNull
      public List<EmergencyContactEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRelationship = CursorUtil.getColumnIndexOrThrow(_cursor, "relationship");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfNotifyOnFall = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnFall");
          final int _cursorIndexOfNotifyOnSOS = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSOS");
          final int _cursorIndexOfNotifyOnSafeZoneExit = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSafeZoneExit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EmergencyContactEntity> _result = new ArrayList<EmergencyContactEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EmergencyContactEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpRelationship;
            if (_cursor.isNull(_cursorIndexOfRelationship)) {
              _tmpRelationship = null;
            } else {
              _tmpRelationship = _cursor.getString(_cursorIndexOfRelationship);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final boolean _tmpNotifyOnFall;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfNotifyOnFall);
            _tmpNotifyOnFall = _tmp_1 != 0;
            final boolean _tmpNotifyOnSOS;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNotifyOnSOS);
            _tmpNotifyOnSOS = _tmp_2 != 0;
            final boolean _tmpNotifyOnSafeZoneExit;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfNotifyOnSafeZoneExit);
            _tmpNotifyOnSafeZoneExit = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EmergencyContactEntity(_tmpId,_tmpUserId,_tmpName,_tmpPhoneNumber,_tmpEmail,_tmpRelationship,_tmpPriority,_tmpIsEnabled,_tmpNotifyOnFall,_tmpNotifyOnSOS,_tmpNotifyOnSafeZoneExit,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<EmergencyContactEntity>> getAllContactsFlow(final String userId) {
    final String _sql = "SELECT * FROM emergency_contacts WHERE userId = ? ORDER BY priority ASC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"emergency_contacts"}, new Callable<List<EmergencyContactEntity>>() {
      @Override
      @NonNull
      public List<EmergencyContactEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRelationship = CursorUtil.getColumnIndexOrThrow(_cursor, "relationship");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfNotifyOnFall = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnFall");
          final int _cursorIndexOfNotifyOnSOS = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSOS");
          final int _cursorIndexOfNotifyOnSafeZoneExit = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSafeZoneExit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EmergencyContactEntity> _result = new ArrayList<EmergencyContactEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EmergencyContactEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpRelationship;
            if (_cursor.isNull(_cursorIndexOfRelationship)) {
              _tmpRelationship = null;
            } else {
              _tmpRelationship = _cursor.getString(_cursorIndexOfRelationship);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final boolean _tmpNotifyOnFall;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfNotifyOnFall);
            _tmpNotifyOnFall = _tmp_1 != 0;
            final boolean _tmpNotifyOnSOS;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNotifyOnSOS);
            _tmpNotifyOnSOS = _tmp_2 != 0;
            final boolean _tmpNotifyOnSafeZoneExit;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfNotifyOnSafeZoneExit);
            _tmpNotifyOnSafeZoneExit = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EmergencyContactEntity(_tmpId,_tmpUserId,_tmpName,_tmpPhoneNumber,_tmpEmail,_tmpRelationship,_tmpPriority,_tmpIsEnabled,_tmpNotifyOnFall,_tmpNotifyOnSOS,_tmpNotifyOnSafeZoneExit,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getContactById(final long contactId,
      final Continuation<? super EmergencyContactEntity> $completion) {
    final String _sql = "SELECT * FROM emergency_contacts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, contactId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EmergencyContactEntity>() {
      @Override
      @Nullable
      public EmergencyContactEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRelationship = CursorUtil.getColumnIndexOrThrow(_cursor, "relationship");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfNotifyOnFall = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnFall");
          final int _cursorIndexOfNotifyOnSOS = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSOS");
          final int _cursorIndexOfNotifyOnSafeZoneExit = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSafeZoneExit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final EmergencyContactEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpRelationship;
            if (_cursor.isNull(_cursorIndexOfRelationship)) {
              _tmpRelationship = null;
            } else {
              _tmpRelationship = _cursor.getString(_cursorIndexOfRelationship);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final boolean _tmpNotifyOnFall;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfNotifyOnFall);
            _tmpNotifyOnFall = _tmp_1 != 0;
            final boolean _tmpNotifyOnSOS;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNotifyOnSOS);
            _tmpNotifyOnSOS = _tmp_2 != 0;
            final boolean _tmpNotifyOnSafeZoneExit;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfNotifyOnSafeZoneExit);
            _tmpNotifyOnSafeZoneExit = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new EmergencyContactEntity(_tmpId,_tmpUserId,_tmpName,_tmpPhoneNumber,_tmpEmail,_tmpRelationship,_tmpPriority,_tmpIsEnabled,_tmpNotifyOnFall,_tmpNotifyOnSOS,_tmpNotifyOnSafeZoneExit,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getEnabledContacts(final String userId,
      final Continuation<? super List<EmergencyContactEntity>> $completion) {
    final String _sql = "SELECT * FROM emergency_contacts WHERE userId = ? AND isEnabled = 1 ORDER BY priority ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EmergencyContactEntity>>() {
      @Override
      @NonNull
      public List<EmergencyContactEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfRelationship = CursorUtil.getColumnIndexOrThrow(_cursor, "relationship");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfNotifyOnFall = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnFall");
          final int _cursorIndexOfNotifyOnSOS = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSOS");
          final int _cursorIndexOfNotifyOnSafeZoneExit = CursorUtil.getColumnIndexOrThrow(_cursor, "notifyOnSafeZoneExit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<EmergencyContactEntity> _result = new ArrayList<EmergencyContactEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EmergencyContactEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
              _tmpPhoneNumber = null;
            } else {
              _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpRelationship;
            if (_cursor.isNull(_cursorIndexOfRelationship)) {
              _tmpRelationship = null;
            } else {
              _tmpRelationship = _cursor.getString(_cursorIndexOfRelationship);
            }
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final boolean _tmpNotifyOnFall;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfNotifyOnFall);
            _tmpNotifyOnFall = _tmp_1 != 0;
            final boolean _tmpNotifyOnSOS;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfNotifyOnSOS);
            _tmpNotifyOnSOS = _tmp_2 != 0;
            final boolean _tmpNotifyOnSafeZoneExit;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfNotifyOnSafeZoneExit);
            _tmpNotifyOnSafeZoneExit = _tmp_3 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new EmergencyContactEntity(_tmpId,_tmpUserId,_tmpName,_tmpPhoneNumber,_tmpEmail,_tmpRelationship,_tmpPriority,_tmpIsEnabled,_tmpNotifyOnFall,_tmpNotifyOnSOS,_tmpNotifyOnSafeZoneExit,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
