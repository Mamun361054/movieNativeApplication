package com.example.myapplication.core.functional


sealed class Either<out L, out R> {
    /**
     * Represent the left side of [Either] class which by convention is a "Failure"
     * */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /**
     *Represent the right side of [Either] class which by convention is a "success"
     */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Either.Left(a)

    fun <R> right(b : R) = Either.Right(b)

    fun fold(fnL: (L) -> Any,fnR: (R)-> Any) : Any = when(this){
        is Left -> fnL(a)
        is Right -> fnR(b)
    }
}