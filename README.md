# Test

## Ashley findings

PooledEngine pools entities and components which implement Poolable. 
For that we must use engine.createComponent. This prevents us to use real constructors and kotlin benefits.
So I think its fine to use normal constructors for everything thats not poolable (most things dont need to be).