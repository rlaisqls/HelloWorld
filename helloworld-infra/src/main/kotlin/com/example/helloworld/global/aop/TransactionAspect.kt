package com.example.helloworld.global.aop

import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.interceptor.*


@Configuration
@Aspect
class TransactionAspect(
    private val transactionManager: TransactionManager
) {

    @Bean
    fun transactionAdviceAdvisor(): Advisor {

        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "within(@com.example.helloworld.global.annotation.UseCase *)"

        return DefaultPointcutAdvisor(pointcut, transactionAdvice())
    }

    @Bean
    fun readOnlyTransactionAdviceAdvisor(): Advisor {

        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "@within(com.example.helloworld.global.annotation.ReadOnlyUseCase)"

        return DefaultPointcutAdvisor(pointcut, readOnlyTransactionAdvice())
    }

    @Bean
    fun transactionAdvice(): TransactionInterceptor {

        val s = NameMatchTransactionAttributeSource(
        )

        val source = MatchAlwaysTransactionAttributeSource()
        val transactionAttribute = RuleBasedTransactionAttribute()

        transactionAttribute.setName("Transaction")
        transactionAttribute.rollbackRules = listOf(
            RollbackRuleAttribute(Exception::class.java)
        )
        source.setTransactionAttribute(transactionAttribute)

        return TransactionInterceptor(transactionManager, source)
    }

    @Bean
    fun readOnlyTransactionAdvice(): TransactionInterceptor {

        val source = MatchAlwaysTransactionAttributeSource()
        val transactionAttribute = RuleBasedTransactionAttribute()

        transactionAttribute.setName("Read-Only Transaction")
        transactionAttribute.isReadOnly = true
        transactionAttribute.rollbackRules = listOf(
            RollbackRuleAttribute(Exception::class.java)
        )
        source.setTransactionAttribute(transactionAttribute)

        return TransactionInterceptor(transactionManager, source)
    }

}