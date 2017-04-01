# Java_Tree

The purpose of this project is to create visually apealing trees in Java using Trees data structures. 

### Change it to your liking!
It is easy modify many properties of the trees.
Here is a list of the things you can change in this code.
1. **Number of trees.**  
Just add more Tree objects `Tree tree = new Tree(15);` to main.
```Java
   public static void main(String[] args) {
      Tree tree = new Tree(limit);
   }
```

2. **Size of trees**  
use `tree.setSize(size);` to change the size of the tree.
3. **Positions**  
3. **Angle position of trees**  
use `tree.setTreeAngle(angle);` to set the angle of the tree. Angle is measured in degrees, where 0 is the right-most of the screen  and 180 is the left-most of the screen.
3. **Angle separation**  
use `tree.setSeparationAngle(Angle);` to set the angle seperation between branches. This angle will dictate how far appart should each branch be from its sibling branch. You can get funny/interesiting looking trees if you play with this property. 

5. **Delay between paintings**    

### Done!
After you are done editing your tree, just call `tree.draw();` to draw it!


![alt text](https://github.com/Maickii/Java_Tree/blob/master/2017-03-05%20(2).png "Tree")
