package com.daxue.studyandroid.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *
 * 内容提供器
 *
 *
 */

public class MyProvider extends ContentProvider {

    /**
     * 初始化内容提供器的时候调用，通常在这里完成对数据库的创建和升级等操作，
     * 返回true标识内容提供器初始化成功，返回false标识失败，注意只有当存在ContentResolver尝试
     * 访问我们程序中的数据时，内容提供器才会被初始化。
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从内容提供器中查询数据，用uri参数来确定查询哪张表，projection参数用于确定查询哪些列，
     * selection和selectionArgs参数用户约束查询哪些行，sortOrder参数用于对结果进行排序，
     * 查询的记过放在Cursor中
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    /**
     * 根据传入的内容 URI 来返回相应的 MIME 类型。
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    /**
     * 向内容提供器中添加一条数据。使用uri参数来确定要添加到的表，待添加的数据保存在values参数中。
     * 添加完成后，返回一个用于表示这条新纪录的URI
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * 从内容提供器中删除数据。使用 uri 参数来确定删除哪一张表中的数据，selection
     * 和 selectionArgs 参数用于约束删除哪些行，被删除的行数将作为返回值返回。
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新内容提供器中已有的数据。使用 uri 参数来确定更新哪一张表中的数据，新数
     * 据保存在 values 参数中，selection 和 selectionArgs 参数用于约束更新哪些行，受影响的
     * 行数将作为返回值返回。
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    /**
     *
     * 一个标准的内容 URI 写法是这样的：
     * content://com.example.app.provider/table1
     *
     *
     *
     */
}
