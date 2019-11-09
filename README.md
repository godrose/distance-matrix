# Distance matrix calculation algorithm 

First we can see that the distances are symmetrical. i.e. dist(A, B) = dist(B,A). 
Additionally dist(A,A) = 0. This means we dont' have to calculate all distances but only the upper triangular or bottom triangular part. 

Let *N* be the overall number of the points. Then the overall number of calculations would sum up to (N - 1) + (N - 2) + ... 1 = (N *** (N -1)) / 2

The division into groups would be as following: take the overall number of calculations and divide it equally between all threads; then wait for completion. The groups don't overlap and all calculations can run in parallel.
