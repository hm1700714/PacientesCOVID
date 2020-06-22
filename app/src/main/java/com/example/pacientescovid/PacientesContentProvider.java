package com.example.pacientescovid;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

    private static final int URI_DOENTES = 200;
    private static final int URI_ID_DOENTES = 201;

    private static final int URI_SINTOMAS = 300;
    private static final int URI_ID_SINTOMAS = 301;

    private static final int URI_ESTADOSAUDE = 400;
    private static final int URI_ID_ESTADOSAUDE = 401;

    private static final int URI_SPRESENTE = 500;
    private static final int URI_ID_SPRESENTE = 501;


    private static final String CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vnd.android.cursor.item/";

    private BdAppOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, DOENTES, URI_DOENTES);
        uriMatcher.addURI(AUTHORITY, DOENTES + "/#", URI_ID_DOENTES);

        uriMatcher.addURI(AUTHORITY, SINTOMAS, URI_SINTOMAS);
        uriMatcher.addURI(AUTHORITY, SINTOMAS + "/#", URI_ID_SINTOMAS);

        uriMatcher.addURI(AUTHORITY, ESTADOSAUDE, URI_ESTADOSAUDE);
        uriMatcher.addURI(AUTHORITY, ESTADOSAUDE + "/#", URI_ID_ESTADOSAUDE);

        uriMatcher.addURI(AUTHORITY, SPRESENTE, URI_SPRESENTE);
        uriMatcher.addURI(AUTHORITY, SPRESENTE + "/#", URI_ID_SPRESENTE);

        return uriMatcher;
    }


    @Override
    public boolean onCreate() {

        openHelper = new BdAppOpenHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase bd = openHelper.getReadableDatabase();
        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_DOENTES:
                return new BdTableDoentes(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_DOENTES:
                return new BdTableDoentes(bd).query(projection, BdTableDoentes._ID + "=?", new String[]{id}, null, null, sortOrder);

            case URI_SINTOMAS:
                return new BdTableSintomas(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_SINTOMAS:
                return new BdTableSintomas(bd).query(projection, BdTableSintomas._ID + "=?", new String[]{id}, null, null, sortOrder);

            case URI_ESTADOSAUDE:
                return new BdTableEstadoSaude(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_ESTADOSAUDE:
                return new BdTableEstadoSaude(bd).query(projection, BdTableEstadoSaude._ID + "=?", new String[]{id}, null, null, sortOrder);

            case URI_SPRESENTE:
                return new BdTableSintomasPresentes(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_SPRESENTE:
                return new BdTableSintomasPresentes(bd).query(projection, BdTableSintomasPresentes._ID + "=?", new String[]{id}, null, null, sortOrder);
            default:
                throw new UnsupportedOperationException("Endereço query inválido: " + uri.getPath());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int codigoUri = getUriMatcher().match(uri);

        switch (codigoUri) {
            case URI_DOENTES:
                return CURSOR_DIR + DOENTES;
            case URI_ID_DOENTES:
                return CURSOR_ITEM + DOENTES;
            case URI_SINTOMAS:
                return CURSOR_DIR + SINTOMAS;
            case URI_ID_SINTOMAS:
                return CURSOR_ITEM + SINTOMAS;
            case URI_ESTADOSAUDE:
                return CURSOR_DIR + ESTADOSAUDE;
            case URI_ID_ESTADOSAUDE:
                return CURSOR_ITEM + ESTADOSAUDE;
            case URI_SPRESENTE:
                return CURSOR_DIR + SPRESENTE;
            case URI_ID_SPRESENTE:
                return CURSOR_ITEM + SPRESENTE;

            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        SQLiteDatabase bd = openHelper.getWritableDatabase();

        long id;

        switch (getUriMatcher().match(uri)) {
            case URI_DOENTES:
                id = (new BdTableDoentes(bd).insert(values));
                break;
            case URI_SINTOMAS:
                id = (new BdTableSintomas(bd).insert(values));
                break;
            case URI_ESTADOSAUDE:
                id = (new BdTableEstadoSaude(bd).insert(values));
                break;
            case URI_SPRESENTE:
                id = (new BdTableSintomasPresentes(bd).insert(values));
                break;
            default:
                throw new UnsupportedOperationException("Endereço insert inválido: " + uri.getPath());
        }

        if (id == -1) {
            throw new SQLException("Não foi possível inserir o registo: " + uri.getPath());
        }

        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase bd = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_ID_DOENTES:
                return new BdTableDoentes(bd).delete(BdTableDoentes._ID + "=?", new String[]{id});

            case URI_ID_SINTOMAS:
                return new BdTableSintomas(bd).delete(BdTableSintomas._ID + "=?", new String[] { id });

            case URI_ID_ESTADOSAUDE:
                return new BdTableEstadoSaude(bd).delete(BdTableEstadoSaude._ID + "=?", new String[] { id });

            case URI_ID_SPRESENTE:
                return new BdTableSintomasPresentes(bd).delete(BdTableSintomasPresentes._ID + "=?", new String[] { id });

            default:
                throw new UnsupportedOperationException("Endereço delete inválido: " + uri.getPath());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)) {
            case URI_ID_DOENTES:
                return new BdTableDoentes(bd).update(values, BdTableDoentes._ID + "=?", new String[] { id });

            case URI_ID_SINTOMAS:
                return new BdTableSintomas(bd).update(values,BdTableSintomas._ID + "=?", new String[] { id });

            case URI_ID_ESTADOSAUDE:
                return new BdTableEstadoSaude(bd).update(values,BdTableEstadoSaude._ID + "=?", new String[] { id });

            case URI_ID_SPRESENTE:
                return new BdTableSintomasPresentes(bd).update(values,BdTableSintomasPresentes._ID + "=?", new String[] { id });

            default:
                throw new UnsupportedOperationException("Endereço de update inválido: " + uri.getPath());
        }
    }
}
