package net.ezra.ui.auth

data class Profile(
    val data: UserData,
    val errorMessege : String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePicture: String?
)