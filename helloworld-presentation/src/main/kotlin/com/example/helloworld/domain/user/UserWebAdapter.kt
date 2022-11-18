package com.example.helloworld.domain.user

import com.example.helloworld.domain.auth.dto.response.TokenResponse
import com.example.helloworld.domain.user.dto.request.*
import com.example.helloworld.domain.user.dto.response.UserInfoResponse
import com.example.helloworld.domain.user.usecase.ChangePasswordUseCase
import com.example.helloworld.domain.user.usecase.SignInUseCase
import com.example.helloworld.domain.user.usecase.SignUpUseCase
import com.example.helloworld.domain.user.usecase.UserInfoUseCase
import com.example.helloworld.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@WebAdapter
@RequestMapping("/users")
class UserWebAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signUp(@Valid @RequestBody request: SignUpWebRequest): TokenResponse {
        return signUpUseCase.execute(
            SignUpRequest(
                email = request.email,
                password = request.password,
                name = request.name
            )
        )
    }

    @PostMapping("/auth")
    fun signIn(@Valid @RequestBody request: SignInWebRequest): TokenResponse {
        return signInUseCase.execute(
            SignInRequest(
                email = request.email,
                password = request.password
            )
        )
    }

    @GetMapping
    fun queryMyInfo(): UserInfoResponse {
        return userInfoUseCase.execute()
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    fun changePassword(@Valid @RequestBody request: ChangePasswordWebRequest) {
        return changePasswordUseCase.execute(
            ChangePasswordRequest(
                oldPassword = request.oldPassword,
                newPassword = request.newPassword
            )
        )
    }
}