# Canvas

## Spec

The program should support the following commands:

* C <width> <height> - Create a new canvas. 
    E.g. create "C 20 4" creates a canvas of dimensions 20 4
    
* L <x1> <y1> <x2> <y2> - Draw a line of x's from x1,y1 to x2,y2. Only straight lines need be supported. 
    E.g. L 1 2 6 2 draws a line from 1,2 to 6,2
    
* R <x1> <y1> <x2> <y2> - Draw a rectangle of x's from x1,y1 to x2,y2.
    E.g. R 16 1 20 3 creates a rectangle with corners at 16,1 and 20,3
    
## Example 
```
> C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
> L 1 2 6 2
----------------------  
|                    |  
|xxxxxx              |  
|                    |  
|                    |  
----------------------  
> R 16 1 20 3
---------------------- 
|               xxxxx| 
|xxxxxx         x   x| 
|               xxxxx| 
|                    | 
---------------------- 
```
