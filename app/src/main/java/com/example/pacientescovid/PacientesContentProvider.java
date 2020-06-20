package com.example.pacientescovid;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PacientesContentProvider extends ContentProvider {


    private static final String AUTHORITY = "com.example.pacientescovid";
    private static final String DOENTES = "doentes";
    private static final String SINTOMAS = "sintomas";
    private static final String ESTADOSAUDE = "estado_saude";
    private static final String SPRESENTE = "spresente";


    private static final Uri ENDERECO_BASE = Uri.parse("content://" + AUTHORITY);
    public static final Uri ENDERECO_DOENTES = Uri.withAppendedPath(ENDERECO_BASE, DOENTES);
    public static final Uri ENDERECO_SINTOMAS = Uri.withAppendedPath(ENDERECO_BASE, SINTOMAS);
    public static final Uri ENDERECO_ESTADOSAUDE = Uri.withAppendedPath(ENDERECO_BASE, ESTADOSAUDE);
    public static final Uri ENDERECO_SPRESENTE = Uri.withAppendedPath(ENDERECO_BASE, SPRESENTE);

    private static final int URI_DOENTES = 100;
    private static final int URI_ID_DOENTES = 101;

    private static final int URI_SINTOMAS = 200;
    private static final int URI_ID_SINTOMAS = 201;

    private static final int URI_ESTADOSAUDE = 300;
    private static final int URI_ID_ESTADOSAUDE = 201;


    private static final String CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vnd.android.cursor.item/";

    private BdAppOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
/*
        uriMatcher.addURI(AUTHORITY, CATEGORIAS, URI_CATEGORIAS);
        uriMatcher.addURI(AUTHORITY, CATEGORIAS + "/#", URI_ID_CATEGORIA);

        uriMatcher.addURI(AUTHORITY, LIVROS, URI_LIVROS);
        uriMatcher.addURI(AUTHORITY, LIVROS + "/#", URI_ID_LIVRO);
*/
        return uriMatcher;
    }





    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
