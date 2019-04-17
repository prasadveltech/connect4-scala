# Canvas

## Spec

The program should support the following commands:

* C <width> <height> - Create a new canvas. 
    E.g. create "C 4 4" creates a canvas of dimensions 4 4
* L <x1> <y1> <x2> <y2> - Draw a line of x's from x1,y1 to x2,y2. Only straight lines need be supported. 
    E.g. L 1 1 1 6 draws a line from 1,1 to 1,6
    
## Example 
```
> C 5 5
 -----
|     |
|     |
|     |
|     |
|     |
|     |
 -----
> L 1 1 1 2
 -----
|xx   |
|     |
|     |
|     |
|     |
|     |
 -----
>    
```
