package com.tsci.learnlanguagewithquotes.core

/**
 * Created by Mehmet Peker on 10.07.2022.
 */
abstract class BaseUseCase<T,R> {
    abstract suspend operator fun invoke(param:T): R
}