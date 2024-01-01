import java.io.Serializable

data class User(val role : String, val firstName: String, val lastName: String,
                val email: String, val password: String, val postcode: String,
                val interestsCenter: Set<String>?
) : Serializable
