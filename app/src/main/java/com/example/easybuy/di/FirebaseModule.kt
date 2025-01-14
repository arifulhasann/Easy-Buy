package com.example.easybuy.di

import com.example.easybuy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
     @Provides
     @Singleton
    fun probideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
    @Provides
    @Singleton
    fun probideFirebaseFirestoreDB(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun probideFirebase(jAuth: FirebaseAuth,db : FirebaseFirestore): AuthRepository{
        return AuthRepository(jAuth,db)
    }

}