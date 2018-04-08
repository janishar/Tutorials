package tinderswipe.swipe.mindorks.demo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile(
        @SerializedName("name") @Expose var name: String,
        @SerializedName("url") @Expose var imageUrl: String,
        @SerializedName("age") @Expose var age: Int,
        @SerializedName("location") @Expose var location: String
)