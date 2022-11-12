package com.example.helloworld.global.aop

import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.interceptor.*

@Configuration
class TransactionAspect(
    private val transactionManager: TransactionManager
) {

    @Bean
    fun transactionAdvice(): TransactionInterceptor {

        val txAttributeSource = NameMatchTransactionAttributeSource()
        val txAttribute = RuleBasedTransactionAttribute()

        txAttribute.rollbackRules = listOf(RollbackRuleAttribute(Exception::class.java))
        txAttribute.propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED

        val txMethods = HashMap<String, TransactionAttribute>()
        txMethods["*"] = txAttribute
        txAttributeSource.setNameMap(txMethods)

        return TransactionInterceptor(transactionManager, txAttributeSource)
    }

    @Bean
    fun transactionAdviceAdvisor(): Advisor {

        val pointcut = AspectJExpressionPointcut()
        pointcut.expression = "within(@com.example.helloworld.global.annotation.UseCase *) ||" +
                "within(@com.example.helloworld.global.annotation.ReadOnlyUseCase *)"

        return DefaultPointcutAdvisor(pointcut, transactionAdvice())
    }

}