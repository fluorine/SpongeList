SpongeList
==========

SpongeList<T> A persistent data structure optimized for insertion.

Version: Alfa 0 (probably full of bugs)

This data type is a list optimized for insertions (deletion is not implemented yet)
For example, this is a typical generalized list:

   (1 2 3 (4 5 6 (7 8)) 9 10)

However, this data structure keeps a balance, so no sublist can contain equal or more 
base items (levels) than the parent list.

So, (1 2 (3 4 4)) would be rewritten to (1 2 3 4 4).

New items cannot be simply added to the end of a list. They "create" a sublist when added.
For example:

  (1 2 3 4)

Add 4 at index 1:

  (1 (2 4) 3 4)
  
So, if you add another item 7 to index 1

  (1 ((7 2) 4) 3 5)

which is unstable, so it degenerates to

  (1 (7 2 4) 3 5)
  
Operations for getting an item works also fine and are pretty fast.
