class AuthService(
    val userRepository: UserRepository,
    var loggedInUser: User? = null
){
    
}