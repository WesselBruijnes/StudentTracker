package com.bruijnes.studenttracker.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private final FirebaseDatabase database;
    private final FirebaseUser user;
    private final DatabaseReference dbRef;

    public FirebaseService() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference(user.getUid());
    }

    public DatabaseReference getDbRef(String key) {
        return dbRef.child(key);
    }
}
