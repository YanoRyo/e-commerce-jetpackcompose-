package com.example.onlineshopapp.domain.repository

import android.util.Log
import com.example.onlineshopapp.domain.model.CategoryModel
import com.example.onlineshopapp.domain.model.ItemsModel
import com.example.onlineshopapp.domain.model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class ExploreRepository @Inject constructor() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): Flow<List<SliderModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Banner")

        val listener = ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(SliderModel::class.java)
                    item?.let { lists.add(it) }
                }
                launch {
                    trySend(lists)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error loading banner: ${error.message}")
                close(error.toException())
            }
        })
        awaitClose { ref.removeEventListener(listener) }
    }.flowOn(Dispatchers.IO)

    fun loadCategory(): Flow<List<CategoryModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Category")

        val listener = ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { lists.add(it) }
                }
                launch {
                    trySend(lists)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error loading banner: ${error.message}")
                close(error.toException())
            }
        })
        awaitClose { ref.removeEventListener(listener) }
    }.flowOn(Dispatchers.IO)

    fun loadBestSeller(): Flow<List<ItemsModel>> = callbackFlow {
        val ref = firebaseDatabase.getReference("Items")

        val listener = ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { lists.add(it) }
                }
                launch {
                    trySend(lists)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error loading banner: ${error.message}")
                close(error.toException())
            }
        })
        awaitClose { ref.removeEventListener(listener) }
    }.flowOn(Dispatchers.IO)


}