FunWithDataStructures
=====================
Playing around with data structures in Java.

PeekingIterator and its Implementation provide some enhanced functionality to your standard iterator. 
As you might have guessed, it is the ability to peek. The following two methods are provided in addition
to the standard Iterator Interface.
```java
public boolean hasOneMoreThanNext();
public E peekAhead();
```
Unforunately, this implementation has the additional overhead of maintaing a copy of the Collection's data.
On the plus side, it is not dependent on any particular Collection implementation.
