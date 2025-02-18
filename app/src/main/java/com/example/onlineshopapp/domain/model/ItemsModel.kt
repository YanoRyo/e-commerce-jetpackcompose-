package com.example.onlineshopapp.domain.model

import java.io.Serializable

data class ItemsModel(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var imageUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var categoryId: String = "",
    var sellerName: String = "",
    var sellerPic: String = "",
    var sellerTel: Int = 0,
) : Serializable {
    companion object {
        fun createDummyItem(): ItemsModel {
            return ItemsModel(
                title = "Dummy Item",
                description = "This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.This is a dummy item for testing.",
                imageUrl = arrayListOf(
                    "https://firebasestorage.googleapis.com/v0/b/project204-1.appspot.com/o/cat1_0.jpg?alt=media&token=48266973-43c8-4531-a9cf-39a44d50c43c",
                    "https://firebasestorage.googleapis.com/v0/b/project204-1.appspot.com/o/cat1_1.jpg?alt=media&token=83674347-1a2b-462f-866c-aede58fcf50f"
                ),
                size = arrayListOf("S", "M", "L"),
                price = 99.99,
                rating = 4.5,
                numberInCart = 0,
                showRecommended = true,
                categoryId = "electronics",
                sellerName = "Dummy Seller",
                sellerPic = "https://example.com/seller.jpg",
                sellerTel = 1234567890
            )
        }
    }
}
