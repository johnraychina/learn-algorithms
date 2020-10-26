
# Interview Questions: Mergesort

## 1. Merging with smaller auxiliary array. 
Suppose that the subarray \mathtt{a[0]}a[0] to \mathtt{a[n-1]}a[n−1] is sorted and the subarray \mathtt{a[n]}a[n] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted. How can you merge the two subarrays so that \mathtt{a[0]}a[0] to \mathtt{a[2*n-1]}a[2∗n−1] is sorted using an auxiliary array of length nn (instead of 2n2n)?

Hint: copy only the left half into the auxiliary array.

```
copy a[0]~a[n-1] to aux array
merge(a, aux, a[n]~a[2n-1])
```

## 2.Counting inversions. 
An inversion in an array a[\,]a[] is a pair of entries a[i]a[i] and a[j]a[j] such that i < ji<j but a[i] > a[j]a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.

Hint: count while mergesorting.

```
mergeSort(a) 
{
    ...
    exch() 
    {
        inversion++;
    }
}
```
## Shuffling a linked list. 
Given a singly-linked list containing nn items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to n \log nnlogn in the worst case.

Hint: design a linear-time subroutine that can take two uniformly shuffled linked lists of sizes n_1 and n_2 
and combined them into a uniformly shuffled linked lists of size n_1 + n_2
