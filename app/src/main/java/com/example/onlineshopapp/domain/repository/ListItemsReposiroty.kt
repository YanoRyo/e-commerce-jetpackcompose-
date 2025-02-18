package com.example.onlineshopapp.domain.repository

import android.util.Log
import com.example.onlineshopapp.domain.model.ItemsModel
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

class ListItemsReposiroty @Inject constructor() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadFiltered(id:String): Flow<List<ItemsModel>> = callbackFlow<List<ItemsModel>> {
        val ref = firebaseDatabase.getReference("Items")
        val query = ref.orderByChild("categoryId").equalTo(id)
        val listener = query.addListenerForSingleValueEvent(object : ValueEventListener {
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
        awaitClose {  }
    }.flowOn(Dispatchers.IO)
}