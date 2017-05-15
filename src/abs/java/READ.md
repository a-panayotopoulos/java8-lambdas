3. Evaluation. Take a look at the signatures of these Stream methods. Are they eager or lazy?

boolean anyMatch(Predicate<? super T> predicate); Eager
Stream<T> limit(long maxSize); Lazy

4. Higher-order functions. Are these Stream functions higher order, and why?

boolean anyMatch(Predicate<? super T> predicate); Higher order because it accepts Interface/function
Stream<T> limit(long maxSize); not higher order because its param and return are object type



