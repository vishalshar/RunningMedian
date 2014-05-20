RunningMedian
=============

Find running median from a stream of input integers

SOLUTION:
We can use a max heap on left side to represent elements that are less than 
effective median, and a min heap on right side to represent elements that 
are greater than effective median.

After processing an incoming element, the number of elements in heaps differ 
at most by 1 element. When both heaps contain same number of elements, we 
pick average of heaps root data as effective median. When the heaps are not 
balanced, we select effective median from the root of heap 
containing more elements. Making sure that both heaps doesn't have 
size difference of more than 1.

Currently code handle only valid inputs and is limited to capacity of 200 Integers.
