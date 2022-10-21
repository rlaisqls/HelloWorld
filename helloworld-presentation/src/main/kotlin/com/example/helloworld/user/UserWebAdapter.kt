package com.example.helloworld.user

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.SignInRequest
import com.example.helloworld.domain.user.dto.request.SignUpRequest
import com.example.helloworld.domain.user.dto.response.UserInfoResponse
import com.example.helloworld.domain.user.usecase.UserInfoUseCase
import com.example.helloworld.domain.user.usecase.SignInUseCase
import com.example.helloworld.domain.user.usecase.SignUpUseCase
import com.example.helloworld.user.dto.request.SignInWebRequest
import com.example.helloworld.user.dto.request.SignUpWebRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserWebAdapter (
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val userInfoUseCase: UserInfoUseCase,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: SignUpWebRequest): TokenResponse {
        return signUpUseCase.execute(
            SignUpRequest(
                username = request.username,
                password = request.password
            )
        )
    }

    @PostMapping("/auth")
    fun signIn(@Valid @RequestBody request: SignInWebRequest): TokenResponse {
        return signInUseCase.execute(
            SignInRequest(
                username = request.username,
                password = request.password,
            )
        )
    }

    @GetMapping
    fun queryMyInfo(): UserInfoResponse {
        return userInfoUseCase.execute()
    }
}